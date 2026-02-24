package com.djekgrif.nativeuisimple.data.repository

import com.djekgrif.nativeuisimple.data.repository.mapper.toDResult
import com.djekgrif.nativeuisimple.data.repository.source.ApiSource
import com.djekgrif.nativeuisimple.domain.model.UserSession
import com.djekgrif.nativeuisimple.domain.model.common.DResult
import com.djekgrif.nativeuisimple.domain.repository.AuthRepository

class AuthRepositoryImp(private val apiSource: ApiSource): AuthRepository {

    override suspend fun signIn(login: String, password: String): DResult<UserSession> {
        return apiSource.signIn(login, password).toDResult { session -> session  }
    }

    override suspend fun signUp(login: String, password: String): DResult<UserSession> {
        return apiSource.signUp(login, password).toDResult { session -> session }
    }
}