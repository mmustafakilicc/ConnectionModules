package com.mklc.locallib.di

import android.content.Context
import androidx.room.Room
import com.mklc.locallib.db.LocalDb
import com.mklc.locallib.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext applicationContext: Context): LocalDb{
        return Room.databaseBuilder(applicationContext, LocalDb::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(localDb: LocalDb): UserDao{
        return localDb.userDao()
    }
}