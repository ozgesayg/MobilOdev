package com.example.homeworkozge.data.service


import com.example.homeworkozge.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun getUsers(): Response<MutableList<User>>
}