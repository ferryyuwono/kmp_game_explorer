package com.ferry.gameexplorer.feature.usecase

import com.ferry.gameexplorer.feature.viewmodel.GameListEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShowOnboardingUseCase {
    fun invoke(input: Flow<GameListEffect>): Flow<Boolean> {
        return input.map {
            it is GameListEffect.None || it is GameListEffect.Reset
        }
    }
}
