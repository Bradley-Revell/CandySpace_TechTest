package com.example.candyspace_tech.Repository

import com.example.candyspace_tech.Api.RetrofitInstance
import com.example.candyspace_tech.model.TopTagResponse
import com.example.candyspace_tech.model.UserResponse
import retrofit2.Response

class Repository {

    suspend fun getUsers(): Response<UserResponse> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getUsersByName(name: String): Response<UserResponse> {
        return RetrofitInstance.api.getUsersByName(name)
    }

    suspend fun getUsersTags(id: Int): Response<TopTagResponse> {
        return RetrofitInstance.api.getUsersTags(id)
    }
}