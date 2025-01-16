package com.ferry.gameexplorer.domain.model

data class GetGameListInput(
    val page: Int = 0,
    val lastData: List<Game> = listOf()
)
