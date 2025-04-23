package com.example.userprofiles

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApiService {
    @GET("fact/")
    suspend fun getRandomFact(): RandomFactResponse
}

data class RandomFactResponse(@SerializedName("fact") val results: String)

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://catfact.ninja/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}
