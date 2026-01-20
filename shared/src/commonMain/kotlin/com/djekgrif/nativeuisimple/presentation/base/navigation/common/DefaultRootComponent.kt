package com.djekgrif.nativeuisimple.presentation.base.navigation.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.WelcomeComponent

class DefaultRootComponent(componentContext: ComponentContext) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<AppScreen>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = AppScreen.serializer(),
            initialConfiguration = AppScreen.Welcome,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: AppScreen, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is AppScreen.Welcome -> RootComponent.Child.Welcome(component = WelcomeComponent(componentContext = componentContext))
            is AppScreen.SignIn -> RootComponent.Child.Welcome(component = WelcomeComponent(componentContext = componentContext))

        }

}