package com.mklc.locallib

import com.mklc.locallib.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ILocalDataManager {
    fun getUser(id: Int): Observable<User>
    fun getAllUser(): Observable<List<User>>
    fun insertUser(user: User): Single<Long>
    fun insertUserList(userList: List<User>): Single<List<Long>>
    fun deleteUser(user: User): Single<Int>
    fun deleteUser(id: Int): Single<Int>
}