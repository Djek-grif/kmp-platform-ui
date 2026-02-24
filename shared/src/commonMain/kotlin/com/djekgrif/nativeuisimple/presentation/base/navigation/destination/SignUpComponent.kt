package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.djekgrif.nativeuisimple.di.getViewModel
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.AppScreen
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.ScreenNavigator
import com.djekgrif.nativeuisimple.presentation.base.ui.signup.SignUpContract
import com.djekgrif.nativeuisimple.presentation.base.ui.signup.SignUpViewModel
import kotlinx.coroutines.launch

class SignUpComponent(componentContext: ComponentContext, navigator: ScreenNavigator): ScreenComponent(componentContext, navigator) {

    val signUpViewModel = getViewModel<SignUpViewModel>()

    init {
        componentScope.launch {
            signUpViewModel.effect.collect { effect ->
                when (effect) {
                    SignUpContract.Effect.Navigation.GoBack -> onBackNavigate()
                    SignUpContract.Effect.Navigation.GoToHome -> navigator.replaceAll(AppScreen.Home)
                }
            }
        }
    }

    override fun clear() {
        signUpViewModel.clear()
    }
}
