package com.djekgrif.nativeuisimple.presentation.base.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface ViewAction
interface ViewState
interface ViewEffect

interface MviViewModel<Action : ViewAction, UiState : ViewState, Effect : ViewEffect> {
    val viewState: StateFlow<UiState>
    val effect: Flow<Effect>
    val action: Flow<Action>
    fun onUIAction(action: Action)

}