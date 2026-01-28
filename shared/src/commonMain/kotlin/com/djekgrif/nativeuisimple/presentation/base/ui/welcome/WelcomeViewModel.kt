package com.djekgrif.nativeuisimple.presentation.base.ui.welcome

import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel

class WelcomeViewModel : BaseViewModel<WelcomeContract.Action, WelcomeContract.State, WelcomeContract.Effect>() {

    override fun buildInitialUiState(): WelcomeContract.State = WelcomeContract.State("Welcome with platform UI!")

    override fun onUIAction(action: WelcomeContract.Action) {
        when (action) {
            WelcomeContract.Action.OnSignInClick -> setEffect { WelcomeContract.Effect.Navigation.GoToSignIn }
            WelcomeContract.Action.OnSignUpClick -> setEffect { WelcomeContract.Effect.Navigation.GoToSignUp }
        }
    }
}