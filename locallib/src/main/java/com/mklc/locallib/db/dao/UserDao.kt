package com.mklc.locallib.db.dao

import androidx.room.Dao
import com.mklc.locallib.model.User

@Dao
abstract class UserDao(clazz: Class<User>): BaseDao<User>(clazz) {
    constructor() : this(User::class.java)
}