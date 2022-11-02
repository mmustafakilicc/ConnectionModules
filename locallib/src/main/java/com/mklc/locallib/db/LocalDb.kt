package com.mklc.locallib.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mklc.locallib.db.dao.UserDao
import com.mklc.locallib.model.User

@Database(entities = [User::class], version = 1)
abstract class LocalDb: RoomDatabase() {
    abstract fun userDao(): UserDao
}