package com.djekgrif.nativeuisimple.data.repository.source

import com.djekgrif.nativeuisimple.data.model.common.ApiResult
import com.djekgrif.nativeuisimple.domain.model.UserSession
import kotlinx.coroutines.delay

class ApiSourceImp: ApiSource {

    override suspend fun signIn(login: String, password: String): ApiResult<UserSession> {
        delay(2000) // simulate network delay
        // simulate successful login
        return if (login.equals("test@test.com", true)) {
            ApiResult.Success(UserSession("token_value", "userId"))
        } else ApiResult.Error(ApiResult.Error.StatusCode.INVALID_CREDENTIALS)
    }

    override suspend fun signUp(login: String, password: String): ApiResult<UserSession> {
        delay(2000) // simulate network delay
        return ApiResult.Success(UserSession("token_value", "userId"))
    }
}