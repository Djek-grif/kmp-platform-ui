package com.djekgrif.nativeuisimple.presentation.base.navigation.common

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.HomeComponent
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.SignInComponent
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.SignUpComponent
import com.djekgrif.nativeuisimple.presentation.base.navigation.destination.WelcomeComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class Welcome(val component: WelcomeComponent) : Child()
        data class SignIn(val component: SignInComponent) : Child()
        data class SignUp(val component: SignUpComponent) : Child()
        data class Home(val component: HomeComponent) : Child()
    }




}