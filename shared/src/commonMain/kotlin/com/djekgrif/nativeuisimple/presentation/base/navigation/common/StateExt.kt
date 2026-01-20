package com.djekgrif.nativeuisimple.presentation.base.navigation.common

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.djekgrif.nativeuisimple.presentation.base.core.MviViewModel
import com.djekgrif.nativeuisimple.presentation.base.core.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <S : ViewState> MviViewModel<*, S, *>.asValue(scope: CoroutineScope): Value<S> {
    val mutableValue = MutableValue(viewState.value)
    scope.launch {
        viewState.collect { newState ->
            mutableValue.value = newState
        }
    }
    return mutableValue
}