package com.djekgrif.nativeuisimple.domain.usecase

import com.djekgrif.nativeuisimple.domain.model.UserSession
import com.djekgrif.nativeuisimple.domain.model.common.DResult
import com.djekgrif.nativeuisimple.domain.model.common.ErrorCode
import com.djekgrif.nativeuisimple.domain.repository.AuthRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SignInUseCaseTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var signInUseCase: SignInUseCase

    @BeforeTest
    fun setUp() {
        authRepository = mock()
        signInUseCase = SignInUseCase(authRepository)
    }

    @Test
    fun `invoke with blank login should return InvalidValidation and not call repository`() = runTest {
        // Given
        val blankLogin = ""
        val validPassword = "password123"

        // When
        val result = signInUseCase.invoke(blankLogin, validPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidValidation, result)
        verifySuspend(VerifyMode.not) { authRepository.signIn(any(), any()) }
    }

    @Test
    fun `invoke with blank password should return InvalidValidation and not call repository`() = runTest {
        // Given
        val validLogin = "user@example.com"
        val blankPassword = ""

        // When
        val result = signInUseCase.invoke(validLogin, blankPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidValidation, result)
        verifySuspend(VerifyMode.not) { authRepository.signIn(any(), any()) }
    }

    @Test
    fun `invoke with whitespace-only login should return InvalidValidation and not call repository`() = runTest {
        // Given
        val whitespaceLogin = "   "
        val validPassword = "password123"

        // When
        val result = signInUseCase.invoke(whitespaceLogin, validPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidValidation, result)
        verifySuspend(VerifyMode.not) { authRepository.signIn(any(), any()) }
    }

    @Test
    fun `invoke with whitespace-only password should return InvalidValidation and not call repository`() = runTest {
        // Given
        val validLogin = "user@example.com"
        val whitespacePassword = "   "

        // When
        val result = signInUseCase.invoke(validLogin, whitespacePassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidValidation, result)
        verifySuspend(VerifyMode.not) { authRepository.signIn(any(), any()) }
    }

    @Test
    fun `invoke with both blank credentials should return InvalidValidation and not call repository`() = runTest {
        // Given
        val blankLogin = ""
        val blankPassword = ""

        // When
        val result = signInUseCase.invoke(blankLogin, blankPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidValidation, result)
        verifySuspend(VerifyMode.not) { authRepository.signIn(any(), any()) }
    }

    @Test
    fun `invoke with valid credentials and successful repository response should return Success`() = runTest {
        // Given
        val validLogin = "user@example.com"
        val validPassword = "password123"
        val userSession = UserSession(token = "auth_token", userId = "user_id")
        everySuspend { authRepository.signIn(validLogin, validPassword) } returns DResult.Success(userSession)

        // When
        val result = signInUseCase.invoke(validLogin, validPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.Success, result)
        verifySuspend(VerifyMode.exactly(1)) { authRepository.signIn(validLogin, validPassword) }
    }

    @Test
    fun `invoke with valid credentials but invalid credentials error should return InvalidCredentials`() = runTest {
        // Given
        val validLogin = "user@example.com"
        val validPassword = "wrongpassword"
        everySuspend { authRepository.signIn(validLogin, validPassword) } returns DResult.Error(ErrorCode.InvalidCredentials)

        // When
        val result = signInUseCase.invoke(validLogin, validPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidCredentials, result)
        verifySuspend(VerifyMode.exactly(1)) { authRepository.signIn(validLogin, validPassword) }
    }

    @Test
    fun `invoke with valid credentials but network error should return InvalidCredentials`() = runTest {
        // Given
        val validLogin = "user@example.com"
        val validPassword = "password123"
        everySuspend { authRepository.signIn(validLogin, validPassword) } returns DResult.Error(ErrorCode.NetworkUnavailable)

        // When
        val result = signInUseCase.invoke(validLogin, validPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidCredentials, result)
        verifySuspend(VerifyMode.exactly(1)) { authRepository.signIn(validLogin, validPassword) }
    }

    @Test
    fun `invoke with valid credentials but unknown error should return InvalidCredentials`() = runTest {
        // Given
        val validLogin = "user@example.com"
        val validPassword = "password123"
        everySuspend { authRepository.signIn(validLogin, validPassword) } returns DResult.Error(ErrorCode.Unknown)

        // When
        val result = signInUseCase.invoke(validLogin, validPassword)

        // Then
        assertEquals(SignInUseCase.SignInUseCaseResult.InvalidCredentials, result)
        verifySuspend(VerifyMode.exactly(1)) { authRepository.signIn(validLogin, validPassword) }
    }

    @Test
    fun `invoke should call repository with exact same parameters`() = runTest {
        // Given
        val login = "specific@email.com"
        val password = "specificPassword123"
        val userSession = UserSession(token = "token", userId = "userId")
        everySuspend { authRepository.signIn(login, password) } returns DResult.Success(userSession)

        // When
        signInUseCase.invoke(login, password)

        // Then
        verifySuspend(VerifyMode.exactly(1)) { authRepository.signIn(login, password) }
    }
}
