package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.djekgrif.nativeuisimple.presentation.base.ui.welcome.WelcomeViewModel

class WelcomeComponent(componentContext: ComponentContext): ScreenComponent(componentContext) {

    val welcomeViewModel = WelcomeViewModel()

    override fun clear() {
        welcomeViewModel.clear()
    }
}