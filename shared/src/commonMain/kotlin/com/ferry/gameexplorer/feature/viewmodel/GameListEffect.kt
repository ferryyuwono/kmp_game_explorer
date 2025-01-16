package com.ferry.gameexplorer.feature.viewmodel

import com.ferry.gameexplorer.domain.model.GetGameListOutput

sealed class GameListEffect {
    data object None: GameListEffect()
    data object Reset: GameListEffect()
    data class Load(
        val lastData: GetGameListOutput = GetGameListOutput(),
    ): GameListEffect()
}
