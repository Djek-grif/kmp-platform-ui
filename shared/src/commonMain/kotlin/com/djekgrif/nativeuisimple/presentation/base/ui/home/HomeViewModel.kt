package com.djekgrif.nativeuisimple.presentation.base.ui.home

import com.djekgrif.nativeuisimple.presentation.base.core.BaseViewModel

class HomeViewModel: BaseViewModel<HomeContract.Action, HomeContract.State, HomeContract.Effect>() {

    override fun buildInitialUiState(): HomeContract.State = HomeContract.State("Welcome to Home!")

    override fun onUIAction(action: HomeContract.Action) {
        when(action) {
            else -> {}
        }
    }
}
