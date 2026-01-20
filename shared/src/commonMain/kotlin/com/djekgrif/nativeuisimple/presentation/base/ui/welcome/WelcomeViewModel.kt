package com.djekgrif.nativeuisimple.presentation.base.ui.welcome

import com.arkivanov.decompose.ComponentContext
import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel

class WelcomeViewModel(componentContext: ComponentContext) :
    BaseViewModel<WelcomeContract.Action, WelcomeContract.State, WelcomeContract.Effect>(
        componentContext
    ) {

    override fun buildInitialUiState(): WelcomeContract.State = WelcomeContract.State("Welcome with platform UI!")

    override fun onUIAction(action: WelcomeContract.Action) {
        when (action) {
            else -> {}
        }
    }
}