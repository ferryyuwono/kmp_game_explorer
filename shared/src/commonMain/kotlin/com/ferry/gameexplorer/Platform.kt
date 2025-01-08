package com.ferry.gameexplorer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform