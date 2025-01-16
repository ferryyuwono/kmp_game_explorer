package com.ferry.gameexplorer.feature.usecase

import com.ferry.gameexplorer.domain.model.GetGameListInput
import com.ferry.gameexplorer.feature.viewmodel.GameListEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class GameListEffectToInputUseCase {
    fun invoke(input: Flow<GameListEffect>): Flow<GetGameListInput> {
        return input.filter {
            it is GameListEffect.Load || it is GameListEffect.Reset
        }.map {
            if (it is GameListEffect.Reset) {
                return@map GetGameListInput()
            }

            val lastData = (it as GameListEffect.Load).lastData
            return@map GetGameListInput(
                page = lastData.page + 1,
                lastData = lastData.data,
            )
        }
    }
}
