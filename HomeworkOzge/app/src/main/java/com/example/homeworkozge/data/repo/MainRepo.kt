package com.example.homeworkozge.data.repo

import com.example.homeworkozge.data.service.ApiClient

class MainRepo {
    suspend fun getUsers() = ApiClient.getApiService().getUsers()
}