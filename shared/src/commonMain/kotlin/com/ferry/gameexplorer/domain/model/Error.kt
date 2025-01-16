package com.ferry.gameexplorer.domain.model

data class Error(
    val code: Int = 400,
    val message: String = "",
)
