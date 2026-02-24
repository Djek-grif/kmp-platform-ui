package com.djekgrif.nativeuisimple.domain.usecase

import com.djekgrif.nativeuisimple.domain.model.common.DResult
import com.djekgrif.nativeuisimple.domain.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {

    sealed interface SignUpUseCaseResult {
        data object Success : SignUpUseCaseResult
        data object InvalidValidation : SignUpUseCaseResult
        data object UserAlreadyExists : SignUpUseCaseResult
    }

    suspend operator fun invoke(login: String, password: String): SignUpUseCaseResult {
        if (login.isBlank() || password.isBlank()) return SignUpUseCaseResult.InvalidValidation
        val result = authRepository.signUp(login, password)
        if (result !is DResult.Success) return SignUpUseCaseResult.UserAlreadyExists
        return SignUpUseCaseResult.Success
    }
}
