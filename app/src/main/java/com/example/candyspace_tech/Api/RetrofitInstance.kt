package com.example.candyspace_tech.Api

import com.example.candyspace_tech.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Create an instance of the retrofit api caller
object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }
}