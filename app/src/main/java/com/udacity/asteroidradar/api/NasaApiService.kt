package com.udacity.asteroidradar.api

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDay
}

object Network {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    val nasaApiService: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }
}