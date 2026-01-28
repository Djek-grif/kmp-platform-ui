package com.djekgrif.nativeuisimple.presentation.base.ui.signin

import com.djekgrif.nativeuisimple.presentation.base.core.ViewAction
import com.djekgrif.nativeuisimple.presentation.base.core.ViewEffect
import com.djekgrif.nativeuisimple.presentation.base.core.ViewState

class SignInContract {

    sealed class Action: ViewAction {
        data object OnContinueClick : Action()
        data object OnBackClick : Action()
    }

    data class State(val isProgress: Boolean = false): ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object GoBack : Navigation()
            object GoToHome : Navigation()
        }
    }
}