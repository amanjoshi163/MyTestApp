package com.mytestapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(val name: String,
                val mobile: String,
                val bookValue: String,
                @PrimaryKey(autoGenerate = true) val id: Int? = null)