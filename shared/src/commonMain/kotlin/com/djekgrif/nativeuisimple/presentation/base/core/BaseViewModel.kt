package com.djekgrif.nativeuisimple.presentation.base.core

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle.Callbacks
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.asValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<Action : ViewAction, UiState : ViewState, Effect : ViewEffect>: MviViewModel<Action, UiState, Effect> {

    abstract fun buildInitialUiState(): UiState
    abstract override fun onUIAction(action: Action)

    private val viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val initialState: UiState by lazy { buildInitialUiState() }

    val viewStateValue: Value<UiState> by lazy { this.asValue(viewModelScope) }

    private val mutableViewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    override val viewState: StateFlow<UiState> = mutableViewState

    private val effectChannel: Channel<Effect> = Channel()
    override val effect = effectChannel.receiveAsFlow()

    override val action: MutableSharedFlow<Action> = MutableSharedFlow()

    init {
        subscribeToAction()
    }

    override fun clear() {
        viewModelJob.cancel()
    }

    private fun subscribeToAction() {
        viewModelScope.launch {
            action.collect {
                onUIAction(it)
            }
        }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        mutableViewState.value = newState
    }

    fun setAction(event: Action) {
        viewModelScope.launch { action.emit(event) }
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { effectChannel.send(effectValue) }
    }

}