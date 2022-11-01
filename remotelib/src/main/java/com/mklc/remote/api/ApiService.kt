package com.mklc.remote.api

import com.mklc.remote.data.model.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/public/v2/users")
    fun loadUsers(): Single<List<UserResponse>>

}