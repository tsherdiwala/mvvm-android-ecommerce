package com.ezmall.di

import com.ezmall.di.modules.uimodules.MainModule
import com.ezmall.di.modules.uimodules.OrderDetailModule
import com.ezmall.di.modules.uimodules.OrderListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        //UIModules
        MainModule::class,
        OrderListModule::class,
        OrderDetailModule::class
    ]
)
interface AppComponent {
    fun inject(application: EzmallApplication)
}