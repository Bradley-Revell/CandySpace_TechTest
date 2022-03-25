package com.example.candyspace_tech.Api

import com.example.candyspace_tech.model.TopTagResponse
import com.example.candyspace_tech.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    //Api call for initial view load
    @GET("/2.3/users?order=asc&max=20&sort=name&site=stackoverflow")
    suspend fun getUsers(): Response<UserResponse>

    //Api call for when a user searches by name
    @GET("/2.3/users?order=asc&max=20&sort=name&site=stackoverflow")
    suspend fun getUsersByName(
        @Query("inname") name: String
    ) : Response<UserResponse>

    //Api call made to get the tags required for the users details
    @GET("/2.3/users/{id}/top-tags?site=stackoverflow")
    suspend fun getUsersTags(
        @Path("id") id: Int
    ) : Response<TopTagResponse>
}