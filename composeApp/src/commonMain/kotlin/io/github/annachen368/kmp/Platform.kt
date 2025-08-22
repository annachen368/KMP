package io.github.annachen368.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform