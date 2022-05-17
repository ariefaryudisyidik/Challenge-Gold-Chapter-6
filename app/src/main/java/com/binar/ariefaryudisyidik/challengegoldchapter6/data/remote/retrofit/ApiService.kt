package com.binar.ariefaryudisyidik.challengegoldchapter6.data.remote.retrofit

import com.binar.ariefaryudisyidik.challengegoldchapter6.data.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>
}