package com.djekgrif.nativeuisimple.data.repository.mapper

import com.djekgrif.nativeuisimple.data.model.common.ApiResult
import com.djekgrif.nativeuisimple.domain.model.common.DResult
import com.djekgrif.nativeuisimple.domain.model.common.ErrorCode

inline fun <T, R> ApiResult<T>.toDResult(mapSuccess: (T) -> R): DResult<R> = when (this) {
    is ApiResult.Success -> DResult.Success(mapSuccess(value))
    is ApiResult.Error -> DResult.Error(ErrorCode.from(statusCode))
}