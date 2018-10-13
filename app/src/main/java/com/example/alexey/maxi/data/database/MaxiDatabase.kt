package com.example.alexey.maxi.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.alexey.maxi.domain.models.Rubric

@Database(entities = arrayOf(Rubric::class), version = 1)
abstract class MaxiDatabase : RoomDatabase() {
    abstract fun createDao(): Dao
}