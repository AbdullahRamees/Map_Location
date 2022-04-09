package com.atarious.map_location.api

import com.atarious.map_location.model.Cities
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices{
    @GET("worldcities")
    fun getCities(): Call<List<Cities>>


    companion object {
        val api_Url = "https://api.npoint.io/b861495b4fbec4288baa/"
        fun create():ApiServices{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(api_Url)
                .build()
            return retrofit.create(ApiServices::class.java)
        }
    }
}