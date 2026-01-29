package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.ScreenNavigator
import com.djekgrif.nativeuisimple.presentation.base.ui.home.HomeViewModel

class HomeComponent(componentContext: ComponentContext, navigator: ScreenNavigator): ScreenComponent(componentContext, navigator) {

    val homeViewModel = HomeViewModel()

    override fun clear() {
        homeViewModel.clear()
    }
}
