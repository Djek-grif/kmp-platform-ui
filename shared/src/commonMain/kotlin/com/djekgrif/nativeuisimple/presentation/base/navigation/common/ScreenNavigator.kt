package com.djekgrif.nativeuisimple.presentation.base.navigation.common

interface ScreenNavigator {

    fun push(screen: AppScreen)
    fun pop()
    fun replaceAll(screen: AppScreen)
}