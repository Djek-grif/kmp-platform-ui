package com.djekgrif.nativeuisimple.presentation.base.navigation.common

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.RootComponent.Child.*
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.SignInComponent
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.SignUpComponent
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.WelcomeComponent

class DefaultRootComponent(componentContext: ComponentContext) : RootComponent, ComponentContext by componentContext, ScreenNavigator {

    private val stackNavigation = StackNavigation<AppScreen>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = stackNavigation,
            serializer = AppScreen.serializer(),
            initialConfiguration = AppScreen.Welcome,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: AppScreen, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is AppScreen.Welcome -> Welcome(component = WelcomeComponent(componentContext = componentContext, navigator = this))
            is AppScreen.SignIn -> SignIn(component = SignInComponent(componentContext = componentContext, navigator = this))
            is AppScreen.SignUp -> SignUp(component = SignUpComponent(componentContext = componentContext, navigator = this))
            is AppScreen.Home -> TODO()
        }

    override fun push(screen: AppScreen) {
        stackNavigation.pushNew(screen)
    }

    override fun pop() {
        stackNavigation.pop()
    }

    override fun replaceAll(screen: AppScreen) {
        stackNavigation.replaceAll(screen)
    }
}