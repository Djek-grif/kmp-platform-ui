package com.djekgrif.nativeuisimple

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.RootComponent
import com.djekgrif.nativeuisimple.presentation.screen.WelcomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(rootComponent: RootComponent) {
    MaterialTheme {

        val stack by rootComponent.stack.subscribeAsState()

        Children(stack = stack) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.Welcome -> WelcomeScreen(instance.component)
            }
        }
    }
}