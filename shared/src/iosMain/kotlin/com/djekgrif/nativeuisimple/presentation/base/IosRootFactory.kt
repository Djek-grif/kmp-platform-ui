package com.djekgrif.nativeuisimple.presentation.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.DefaultRootComponent
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.RootComponent

class IosRootFactory {

    fun createRoot(): RootComponent {
        val lifecycle = LifecycleRegistry()
        val componentContext: ComponentContext = DefaultComponentContext(lifecycle = lifecycle)
        return DefaultRootComponent(componentContext)
    }
}