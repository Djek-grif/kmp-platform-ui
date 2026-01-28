package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.AppScreen
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.ScreenNavigator
import com.djekgrif.nativeuisimple.presentation.base.ui.welcome.WelcomeContract
import com.djekgrif.nativeuisimple.presentation.base.ui.welcome.WelcomeViewModel
import kotlinx.coroutines.launch

class WelcomeComponent(componentContext: ComponentContext, navigator: ScreenNavigator): ScreenComponent(componentContext, navigator) {

    val welcomeViewModel = WelcomeViewModel()

    init {
        componentScope.launch {
            welcomeViewModel.effect.collect { effect ->
                when (effect) {
                    WelcomeContract.Effect.Navigation.GoToSignIn -> onNavigate(AppScreen.SignIn)
                    WelcomeContract.Effect.Navigation.GoToSignUp -> onNavigate(AppScreen.SignUp)
                }
            }
        }
    }

    override fun clear() {
        welcomeViewModel.clear()
    }
}