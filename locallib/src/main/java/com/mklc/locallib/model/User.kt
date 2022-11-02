package com.mklc.locallib.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name :String,
    val gender: String
)