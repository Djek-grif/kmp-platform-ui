package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle.Callbacks

abstract class ScreenComponent(componentContext: ComponentContext): ComponentContext by componentContext {

    abstract fun clear()

    init {
        lifecycle.subscribe(object : Callbacks {
            override fun onDestroy() {
                clear()
            }
        })
    }
}