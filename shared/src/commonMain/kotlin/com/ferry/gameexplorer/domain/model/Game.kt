package com.ferry.gameexplorer.domain.model

data class Game(
    val id: Int,
    val name: String,
    val released: Long,
    val backgroundImage: String,
    val metacritic: Int,
)
