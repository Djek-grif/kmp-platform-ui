package com.djekgrif.nativeuisimple.presentation.base.ui.welcome

import com.djekgrif.nativeuisimple.presentation.base.core.ViewAction
import com.djekgrif.nativeuisimple.presentation.base.core.ViewEffect
import com.djekgrif.nativeuisimple.presentation.base.core.ViewState

class WelcomeContract {

    sealed class Action: ViewAction {
        data object HomeOpened : Action()
    }

    data class State(val title: String, val isProgress: Boolean = false): ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {

        }
    }
}