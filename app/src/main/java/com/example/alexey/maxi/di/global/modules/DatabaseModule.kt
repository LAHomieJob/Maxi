package com.example.alexey.maxi.di.global.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.alexey.maxi.data.database.MaxiDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context) =
            Room.databaseBuilder(context, MaxiDatabase::class.java, "maxi.db").build()

    @Singleton
    @Provides
    fun providesDao(demoDatabase: MaxiDatabase) = demoDatabase.createDao()

}