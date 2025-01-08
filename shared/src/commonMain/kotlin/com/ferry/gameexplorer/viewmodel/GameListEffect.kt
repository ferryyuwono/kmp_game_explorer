package com.ferry.gameexplorer.viewmodel

sealed class GameListEffect {
    data object None: GameListEffect()
    data object Reset: GameListEffect()
    data class Load(val page: Int): GameListEffect()
}
