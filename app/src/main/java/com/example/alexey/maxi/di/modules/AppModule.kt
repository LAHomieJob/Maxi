package com.example.alexey.maxi.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context){
    @Provides
    fun provideContext() = context
}
