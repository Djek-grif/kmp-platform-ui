package com.djekgrif.nativeuisimple.domain.model.common

enum class ErrorCode(val code: Int) {

    InvalidCredentials(1),
    NetworkUnavailable(2),
    Unknown(999);

    companion object {
        fun from(code: Int): ErrorCode =
            entries.firstOrNull { it.code == code } ?: Unknown
    }
}