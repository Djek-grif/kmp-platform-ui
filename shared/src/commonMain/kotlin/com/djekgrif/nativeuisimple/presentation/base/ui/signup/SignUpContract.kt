package com.djekgrif.nativeuisimple.presentation.base.ui.signup

import com.djekgrif.nativeuisimple.presentation.base.core.ViewAction
import com.djekgrif.nativeuisimple.presentation.base.core.ViewEffect
import com.djekgrif.nativeuisimple.presentation.base.core.ViewState

class SignUpContract {

    sealed class Action: ViewAction {
        data object OnContinueClick : Action()
        data object OnBackClick : Action()
        data class OnLoginChanged(val value: String) : Action()
        data class OnPasswordChanged(val value: String) : Action()
        data object OnUserAlreadyExistsDialogOk : Action()
        data object OnInvalidValidationDialogOk : Action()
    }

    data class State(
        val login: String = "",
        val password: String = "",
        val isProgress: Boolean = false,
        val showUserAlreadyExistsDialog: Boolean = false,
        val showInvalidValidationDialog: Boolean = false
    ) : ViewState

    sealed class Effect : ViewEffect {
        sealed class Navigation : Effect() {
            object GoBack : Navigation()
            object GoToHome : Navigation()
        }
    }
}
