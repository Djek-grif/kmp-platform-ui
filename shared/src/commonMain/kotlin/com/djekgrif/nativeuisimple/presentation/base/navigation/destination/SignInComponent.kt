package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.AppScreen
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.ScreenNavigator
import com.djekgrif.nativeuisimple.presentation.base.ui.signin.SignInContract
import com.djekgrif.nativeuisimple.presentation.base.ui.signin.SignInViewModel
import kotlinx.coroutines.launch

class SignInComponent(componentContext: ComponentContext, navigator: ScreenNavigator): ScreenComponent(componentContext, navigator) {

    val signInViewModel = SignInViewModel()

    init {
        componentScope.launch {
            signInViewModel.effect.collect { effect ->
                when (effect) {
                    SignInContract.Effect.Navigation.GoBack -> onBackNavigate()
                    SignInContract.Effect.Navigation.GoToHome -> navigator.replaceAll(AppScreen.Home)
                }
            }
        }
    }

    override fun clear() {
        signInViewModel.clear()
    }
}