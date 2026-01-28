package com.djekgrif.nativeuisimple.presentation.base.navigation.common

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppScreen {
    @Serializable
    data object Welcome : AppScreen

    @Serializable
    data object SignIn: AppScreen

    @Serializable
    data object SignUp: AppScreen

    @Serializable
    data object Home : AppScreen
}