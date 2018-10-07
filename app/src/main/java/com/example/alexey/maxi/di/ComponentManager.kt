package com.example.alexey.maxi.di

import android.content.Context
import com.example.alexey.maxi.di.modules.AppModule
import com.example.alexey.maxi.di.modules.components.AppComponent
import com.example.alexey.maxi.di.modules.components.DaggerAppComponent

class ComponentManager(context: Context) {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().
                appModule(AppModule(context)).
                build()
    }
}