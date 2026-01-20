package com.djekgrif.nativeuisimple.presentation.base.navigation.common

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.WelcomeComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class Welcome(val component: WelcomeComponent) : Child()
    }
}