package com.mklc.connectionmodules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadUsers()
    }

    private fun loadUsers(){
        userRepository.loadUsers()
            .observeOn(Schedulers.io())
            .subscribe({
            Timber.e(it.size.toString())
        },{
            Timber.e(it)
        })
    }
}