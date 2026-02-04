package com.djekgrif.nativeuisimple.presentation.base.ui.signin

import com.djekgrif.nativeuisimple.presentation.base.core.ViewAction
import com.djekgrif.nativeuisimple.presentation.base.core.ViewEffect
import com.djekgrif.nativeuisimple.presentation.base.core.ViewState

class SignInContract {

    sealed class Action: ViewAction {
        data object OnBackClick : Action()
        data object OnContinueClick : Action()
        data class OnLoginChanged(val value: String) : Action()
        data class OnPasswordChanged(val value: String) : Action()

        data object OnInvalidValidationDialogOk : Action()
        data object OnInvalidCredentialsDialogOk : Action()
        data object OnNetworkErrorDialogOk : Action()
    }

    data class State(val login: String,
                     val password: String,
                     val isProgress: Boolean = false,
                     val showInvalidValidationDialog: Boolean = false,
                     val showInvalidCredentialsDialog: Boolean = false,
                     val showNetworkErrorDialog: Boolean = false): ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object GoBack : Navigation()
            object GoToHome : Navigation()
        }
    }
}