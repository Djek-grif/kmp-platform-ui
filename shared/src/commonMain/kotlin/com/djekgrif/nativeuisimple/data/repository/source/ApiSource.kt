package com.djekgrif.nativeuisimple.data.repository.source

import com.djekgrif.nativeuisimple.data.model.common.ApiResult
import com.djekgrif.nativeuisimple.domain.model.UserSession

interface ApiSource {
    suspend fun signIn(login: String, password: String): ApiResult<UserSession>
    suspend fun signUp(login: String, password: String): ApiResult<UserSession>
}