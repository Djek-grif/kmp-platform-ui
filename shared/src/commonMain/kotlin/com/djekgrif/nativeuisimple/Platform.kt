package com.djekgrif.nativeuisimple

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform