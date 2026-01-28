package com.djekgrif.nativeuisimple.presentation.base.ui.signup

import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel

class SignUpViewModel: BaseViewModel<SignUpContract.Action, SignUpContract.State, SignUpContract.Effect>() {

    override fun buildInitialUiState(): SignUpContract.State = SignUpContract.State()

    override fun onUIAction(action: SignUpContract.Action) {
        when(action) {
            SignUpContract.Action.OnBackClick -> setEffect { SignUpContract.Effect.Navigation.GoBack }
            SignUpContract.Action.OnContinueClick -> { }//TODO Add Home navigation effect
        }
    }
}
