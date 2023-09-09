package com.example.smartbartender

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Define your API methods here

    @GET("test") // Use the actual endpoint path here
    fun getExampleData(): Call<Void>

    @GET("pump1")
    fun sendPumpData(): Call<Void>

    // Add more API methods as needed
}