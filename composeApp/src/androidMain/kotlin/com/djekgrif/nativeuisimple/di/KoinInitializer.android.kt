package com.djekgrif.nativeuisimple.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

fun initKoin(context: Context) {
    initKoin {
        androidLogger()
        androidContext(context)
    }
}
