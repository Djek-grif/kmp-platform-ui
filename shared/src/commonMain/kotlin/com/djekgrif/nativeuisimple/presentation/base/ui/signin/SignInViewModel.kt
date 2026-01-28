package com.djekgrif.nativeuisimple.presentation.base.ui.signin

import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel

class SignInViewModel: BaseViewModel<SignInContract.Action, SignInContract.State, SignInContract.Effect>() {

    override fun buildInitialUiState(): SignInContract.State = SignInContract.State()

    override fun onUIAction(action: SignInContract.Action) {
        when(action) {
            SignInContract.Action.OnBackClick -> setEffect { SignInContract.Effect.Navigation.GoBack }
            SignInContract.Action.OnContinueClick -> setEffect { SignInContract.Effect.Navigation.GoToHome }
        }
    }
}