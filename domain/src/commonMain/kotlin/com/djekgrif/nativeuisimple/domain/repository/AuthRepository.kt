package com.djekgrif.nativeuisimple.domain.repository

import com.djekgrif.nativeuisimple.domain.model.UserSession
import com.djekgrif.nativeuisimple.domain.model.common.DResult

interface AuthRepository {

    suspend fun signIn(login: String, password: String): DResult<UserSession>

    suspend fun signUp(login: String, password: String): DResult<UserSession>

}