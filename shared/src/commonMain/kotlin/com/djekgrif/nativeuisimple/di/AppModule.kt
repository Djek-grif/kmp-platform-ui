package com.djekgrif.nativeuisimple.di

import com.djekgrif.nativeuisimple.data.repository.AuthRepositoryImp
import com.djekgrif.nativeuisimple.data.repository.source.ApiSource
import com.djekgrif.nativeuisimple.data.repository.source.ApiSourceImp
import com.djekgrif.nativeuisimple.domain.repository.AuthRepository
import com.djekgrif.nativeuisimple.domain.usecase.SignInUseCase
import com.djekgrif.nativeuisimple.presentation.base.ui.home.HomeViewModel
import com.djekgrif.nativeuisimple.presentation.base.ui.signin.SignInViewModel
import com.djekgrif.nativeuisimple.presentation.base.ui.signup.SignUpViewModel
import com.djekgrif.nativeuisimple.presentation.base.ui.welcome.WelcomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    // Data sources
    singleOf(::ApiSourceImp) bind ApiSource::class

    // Repositories
    singleOf(::AuthRepositoryImp) bind AuthRepository::class

    // Use cases
    factoryOf(::SignInUseCase)

    // ViewModels
    factoryOf(::HomeViewModel)
    factoryOf(::SignInViewModel)
    factoryOf(::SignUpViewModel)
    factoryOf(::WelcomeViewModel)
}
