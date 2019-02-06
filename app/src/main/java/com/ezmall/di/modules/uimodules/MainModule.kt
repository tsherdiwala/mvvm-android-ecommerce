package com.ezmall.di.modules.uimodules

import com.ezmall.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
}