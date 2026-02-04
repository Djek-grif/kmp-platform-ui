package com.djekgrif.nativeuisimple.presentation.base.ui.signin

import com.djekgrif.nativeuisimple.domain.usecase.SignInUseCase
import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(private val signInUseCase: SignInUseCase): BaseViewModel<SignInContract.Action, SignInContract.State, SignInContract.Effect>() {

    override fun buildInitialUiState(): SignInContract.State = SignInContract.State("", "")

    override fun onUIAction(action: SignInContract.Action) {
        when(action) {
            is SignInContract.Action.OnBackClick -> setEffect { SignInContract.Effect.Navigation.GoBack }
            is SignInContract.Action.OnContinueClick -> signIn()
            is SignInContract.Action.OnLoginChanged -> setState { copy(login = action.value) }
            is SignInContract.Action.OnPasswordChanged -> setState { copy(password = action.value) }
        }
    }

    fun signIn() {
        val login = viewState.value.login
        val password = viewState.value.password

        viewModelScope.launch {
            setState { copy(isProgress = true) }
            val result = withContext(Dispatchers.IO) { signInUseCase.invoke(login, password) }
            setState { copy(isProgress = false) }

            when (result) {
                SignInUseCase.SignInUseCaseResult.InvalidCredentials -> {}
                SignInUseCase.SignInUseCaseResult.InvalidValidation -> {}
                SignInUseCase.SignInUseCaseResult.Success -> setEffect { SignInContract.Effect.Navigation.GoToHome }
            }
        }
    }
}