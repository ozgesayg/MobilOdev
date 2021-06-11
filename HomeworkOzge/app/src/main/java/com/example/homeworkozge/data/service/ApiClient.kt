package com.example.homeworkozge.data.service


import com.example.homeworkozge.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getApiService(): ApiService {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://5e510330f2c0d300147c034c.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitBuilder.create(ApiService::class.java)
    }
}