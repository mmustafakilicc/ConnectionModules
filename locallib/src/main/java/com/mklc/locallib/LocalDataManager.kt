package com.mklc.locallib

import com.mklc.locallib.db.dao.UserDao
import com.mklc.locallib.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalDataManager @Inject constructor(
    private val userDao: UserDao
) : ILocalDataManager {
    override fun getUser(id: Int): Observable<User> {
        return userDao.get(id)
    }

    override fun getAllUser(): Observable<List<User>> {
        return userDao.getAll()
    }

    override fun insertUser(user: User): Single<Long> {
        return userDao.insert(user)
    }

    override fun insertUserList(userList: List<User>): Single<List<Long>> {
        return userDao.insert(userList)
    }

    override fun deleteUser(user: User): Single<Int> {
        return userDao.delete(user)
    }

    override fun deleteUser(id: Int): Single<Int> {
        return userDao.delete(id)
    }
}