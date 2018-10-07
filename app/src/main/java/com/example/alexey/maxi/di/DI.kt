package com.example.alexey.maxi.di

import android.app.Application

object DI {

    private lateinit var componentManager: ComponentManager

    fun init(application: Application) {
        componentManager = ComponentManager(application)
    }

    fun componentManager(): ComponentManager = componentManager

}