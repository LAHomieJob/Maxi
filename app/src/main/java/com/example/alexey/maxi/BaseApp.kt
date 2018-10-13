package com.example.alexey.maxi

import android.app.Application
import com.arellomobile.mvp.MvpFacade
import com.example.alexey.maxi.di.DI

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        DI.init(this)
    }
}