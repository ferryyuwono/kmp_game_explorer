package com.ferry.gameexplorer.domain.model

data class GetGameListOutput(
    val data: List<Game> = listOf(),
    val page: Int = 0,
    val isLastPage: Boolean = true,
    val lastData: List<Game> = listOf(),
)