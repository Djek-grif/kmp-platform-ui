package com.djekgrif.nativeuisimple.domain.usecase

import com.djekgrif.nativeuisimple.domain.model.common.DResult
import com.djekgrif.nativeuisimple.domain.repository.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {

    sealed interface SignInUseCaseResult {
        data object Success: SignInUseCaseResult
        data object InvalidValidation: SignInUseCaseResult
        data object InvalidCredentials: SignInUseCaseResult

        data object NetworkError: SignInUseCaseResult
    }

    suspend operator fun invoke(login: String, password: String): SignInUseCaseResult {
        if (login.isBlank() || password.isBlank()) return SignInUseCaseResult.InvalidValidation
        val result = authRepository.signIn(login, password)
        if(result !is DResult.Success) return SignInUseCaseResult.InvalidCredentials
        //TODO add user session to local storage
        return SignInUseCaseResult.Success
    }
}