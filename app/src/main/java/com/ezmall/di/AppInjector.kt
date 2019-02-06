package com.ezmall.di

object AppInjector {

    fun init(application: EzmallApplication) {
        DaggerAppComponent
            .create()
            .inject(application)
    }

}