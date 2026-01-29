package com.djekgrif.nativeuisimple.presentation.base.ui.home

import com.djekgrif.nativeuisimple.presentation.base.core.ViewAction
import com.djekgrif.nativeuisimple.presentation.base.core.ViewEffect
import com.djekgrif.nativeuisimple.presentation.base.core.ViewState

class HomeContract {

    sealed class Action: ViewAction

    data class State(val welcomeMessage: String): ViewState

    sealed class Effect : ViewEffect
}
