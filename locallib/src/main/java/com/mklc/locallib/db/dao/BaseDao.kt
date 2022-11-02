package com.mklc.locallib.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mklc.locallib.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class BaseDao<T : Any>(private val clazz: Class<T>) {

    @Insert
    abstract fun insert(t: T): Single<Long>

    @Insert
    abstract fun insert(t: List<T>): Single<List<Long>>

    @Update
    abstract fun update(t: T): Single<Int>

    @Delete
    abstract fun delete(t: T): Single<Int>

    @RawQuery(observedEntities = [User::class])
    abstract fun getAll(query: SupportSQLiteQuery): Observable<List<T>>
    fun getAll(): Observable<List<T>> {
        return getAll(SimpleSQLiteQuery("select * from " + getTableName()))
    }

    @RawQuery(observedEntities = [User::class])
    abstract fun get(query: SupportSQLiteQuery): Observable<T>
    fun get(id: Int): Observable<T> {
        return get(SimpleSQLiteQuery("select * from " + getTableName() + " where id = " + id))
    }

    @RawQuery(observedEntities = [User::class])
    protected abstract fun deleteAll(query: SupportSQLiteQuery): Single<Int>
    fun deleteAll(): Single<Int> {
        return deleteAll(SimpleSQLiteQuery("delete from " + getTableName()))
    }

    @RawQuery(observedEntities = [User::class])
    abstract fun delete(query: SupportSQLiteQuery): Single<Int>
    fun delete(id: Int): Single<Int> {
        return delete(SimpleSQLiteQuery("delete from " + getTableName() + " where id = " + id))
    }

    open fun getTableName() : String? {
        return clazz.simpleName
    }
}