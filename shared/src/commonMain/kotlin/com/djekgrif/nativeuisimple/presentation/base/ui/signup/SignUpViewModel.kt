package com.djekgrif.nativeuisimple.presentation.base.ui.signup

import com.djekgrif.nativeuisimple.domain.usecase.SignUpUseCase
import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : BaseViewModel<SignUpContract.Action, SignUpContract.State, SignUpContract.Effect>() {

    override fun buildInitialUiState(): SignUpContract.State = SignUpContract.State()

    override fun onUIAction(action: SignUpContract.Action) {
        when (action) {
            is SignUpContract.Action.OnBackClick -> setEffect { SignUpContract.Effect.Navigation.GoBack }
            is SignUpContract.Action.OnContinueClick -> signUp()
            is SignUpContract.Action.OnLoginChanged -> setState { copy(login = action.value) }
            is SignUpContract.Action.OnPasswordChanged -> setState { copy(password = action.value) }
            is SignUpContract.Action.OnUserAlreadyExistsDialogOk -> setState { copy(showUserAlreadyExistsDialog = false) }
            is SignUpContract.Action.OnInvalidValidationDialogOk -> setState { copy(showInvalidValidationDialog = false) }
        }
    }

    private fun signUp() {
        val login = viewState.value.login
        val password = viewState.value.password

        viewModelScope.launch {
            setState { copy(isProgress = true) }
            val result = withContext(Dispatchers.IO) { signUpUseCase.invoke(login, password) }
            setState { copy(isProgress = false) }

            when (result) {
                SignUpUseCase.SignUpUseCaseResult.InvalidValidation -> setState { copy(showInvalidValidationDialog = true) }
                SignUpUseCase.SignUpUseCaseResult.UserAlreadyExists -> setState { copy(showUserAlreadyExistsDialog = true) }
                SignUpUseCase.SignUpUseCaseResult.Success -> setEffect { SignUpContract.Effect.Navigation.GoToHome }
            }
        }
    }
}
