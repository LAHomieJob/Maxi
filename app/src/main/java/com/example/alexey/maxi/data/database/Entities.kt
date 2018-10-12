package com.example.alexey.maxi.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rubrics")
data class Rubric(

        @PrimaryKey
        val id: Int?,

        val name: String,

        @ColumnInfo(name = "parent_rubric_id")
        val parentRubricId: Int)
