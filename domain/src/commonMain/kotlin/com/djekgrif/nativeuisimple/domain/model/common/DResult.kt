package com.djekgrif.nativeuisimple.domain.model.common

sealed class DResult<out R> {
    data class Success<out R>(val data: R) : DResult<R>()
    data class Error(val errorCode: ErrorCode) : DResult<Nothing>()
}