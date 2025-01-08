package com.ferry.gameexplorer.viewmodel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

open class MainViewModel : ViewModel() {
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
     * A [StateFlow] that emits the list of [String].
     */
    @NativeCoroutinesState
    val gameList = _gameListEffect.filter {
        it !is GameListEffect.None
    }.map {

        if (it is GameListEffect.Reset) {
            listOf()
        } else {
            listOf(
                "Test 1",
                "Test 2",
                "Test 3",
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    fun load() {
        _gameListEffect.update { GameListEffect.Load(1) }
    }

    fun reset() {
        _gameListEffect.update { GameListEffect.Reset }
    }
}
