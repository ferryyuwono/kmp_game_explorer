package com.ferry.gameexplorer.feature.viewmodel

import com.ferry.gameexplorer.domain.model.Error
import com.ferry.gameexplorer.domain.model.GetGameListOutput
import com.ferry.gameexplorer.domain.usecase.GetGameListUseCase
import com.ferry.gameexplorer.feature.usecase.GameListEffectToInputUseCase
import com.ferry.gameexplorer.feature.usecase.GetGameListErrorUseCase
import com.ferry.gameexplorer.feature.usecase.GetGameListSuccessUseCase
import com.ferry.gameexplorer.feature.usecase.ShowOnboardingUseCase
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow

open class MainViewModel(
    getGameListUseCase: GetGameListUseCase,
) : ViewModel() {
    private val _gameListEffect = MutableStateFlow<GameListEffect>(
        viewModelScope,
        GameListEffect.None,
    )
    /**
     * A [StateFlow] that emits the applied [GameListEffect].
     */
    @NativeCoroutinesState
    val gameListEffect = _gameListEffect.asStateFlow()

    /**
     * A [StateFlow] that emits show onboarding page.
     */
    @NativeCoroutinesState
    val isShowOnBoardingState = ShowOnboardingUseCase().invoke(
        _gameListEffect,
    ).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        true,
    )

    private val _getGameListInputFlow = GameListEffectToInputUseCase().invoke(
        _gameListEffect
    )

    private val _getGameListOutputFlow = getGameListUseCase.invoke(
        _getGameListInputFlow,
    )

    /**
     * A [StateFlow] that emits the list of [String].
     */
    @NativeCoroutinesState
    val getGameListSuccessState = GetGameListSuccessUseCase().invoke(
        _getGameListOutputFlow,
    ).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        GetGameListOutput(),
    )

    /**
     * A [StateFlow] that emits show onboarding page.
     */
    @NativeCoroutinesState
    val getGameListErrorState = GetGameListErrorUseCase().invoke(
        _getGameListOutputFlow,
    ).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        Error(),
    )

    fun start() {
        viewModelScope.launch {
            _gameListEffect.emit(GameListEffect.Load())
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            _gameListEffect.emit(GameListEffect.Load(
                lastData = getGameListSuccessState.value,
            ))
        }
    }

    fun reset() {
        viewModelScope.launch {
            _gameListEffect.emit(GameListEffect.Reset)
        }
    }
}
