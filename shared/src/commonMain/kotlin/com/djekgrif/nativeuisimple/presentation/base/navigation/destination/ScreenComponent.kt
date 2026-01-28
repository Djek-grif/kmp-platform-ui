package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle.Callbacks
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.AppScreen
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.ScreenNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class ScreenComponent(componentContext: ComponentContext, private val navigator: ScreenNavigator): ComponentContext by componentContext {

    protected val componentScope: CoroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main.immediate
    )

    abstract fun clear()

    init {
        lifecycle.subscribe(object : Callbacks {
            override fun onDestroy() {
                clear()
                componentScope.cancel()
            }
        })
    }

    fun onNavigate(appScreen: AppScreen) {
        navigator.push(appScreen)
    }

    fun onBackNavigate() {
        navigator.pop()
    }

    fun onReplaceAll(appScreen: AppScreen) {
        navigator.replaceAll(appScreen)
    }
}