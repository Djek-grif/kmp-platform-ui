package com.djekgrif.nativeuisimple.di

import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.ScreenComponent
import org.koin.core.parameter.ParametersDefinition

inline fun <reified T : BaseViewModel<*, *, *>> ScreenComponent.getViewModel(
    noinline parameters: ParametersDefinition? = null,
): T {
    val scopeId = this.hashCode().toString()
    val scope = getKoin().getOrCreateScope(scopeId, org.koin.core.qualifier.named("component"))

    lifecycle.doOnDestroy {
        scope.close()
    }

    return scope.get(parameters = parameters)
}