package com.djekgrif.nativeuisimple.presentation.base.navigation.destination

import com.arkivanov.decompose.ComponentContext
import com.djekgrif.nativeuisimple.data.repository.AuthRepositoryImp
import com.djekgrif.nativeuisimple.data.repository.source.ApiSourceImp
import com.djekgrif.nativeuisimple.domain.usecase.SignInUseCase
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.AppScreen
import com.djekgrif.nativeuisimple.presentation.base.navigation.common.ScreenNavigator
import com.djekgrif.nativeuisimple.presentation.base.ui.signin.SignInContract
import com.djekgrif.nativeuisimple.presentation.base.ui.signin.SignInViewModel
import kotlinx.coroutines.launch

class SignInComponent(componentContext: ComponentContext, navigator: ScreenNavigator): ScreenComponent(componentContext, navigator) {

    val signInViewModel = SignInViewModel(SignInUseCase(AuthRepositoryImp(ApiSourceImp()))) //TODO replace with DI

    init {
        componentScope.launch {
            signInViewModel.effect.collect { effect ->
                when (effect) {
                    SignInContract.Effect.Navigation.GoBack -> onBackNavigate()
                    SignInContract.Effect.Navigation.GoToHome -> navigator.replaceAll(AppScreen.Home)
                }
            }
        }
    }

    override fun clear() {
        signInViewModel.clear()
    }
}