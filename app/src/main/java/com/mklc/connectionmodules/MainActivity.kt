package com.mklc.connectionmodules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.mklc.locallib.LocalDataManager
import com.mklc.locallib.model.User
import com.mklc.remote.data.model.response.UserResponse
import com.mklc.remote.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var localDataManager: LocalDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadUsers()
    }

    private fun loadUsers() {
        userRepository.loadUsers()
            .observeOn(Schedulers.io())
            .subscribe({
                saveUserToDb(it)
            }, {
                Timber.e(it)
            })
    }

    private fun saveUserToDb(responseList: List<UserResponse>) {
        val response = responseList[0]
        val user = User(name = response.name, gender = response.gender)
        localDataManager.insertUser(user)
            .subscribe({
                getUsers()
            }, {
                Timber.e(it)
            })
    }

    private fun getUsers() {
        localDataManager.getAllUser()
            .subscribe({
                Timber.e(Gson().toJson(it[0]))
            }, {
                Timber.e(it)
            })
    }
}