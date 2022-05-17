package com.binar.ariefaryudisyidik.challengegoldchapter6.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.ariefaryudisyidik.challengegoldchapter6.data.remote.response.Movie
import com.binar.ariefaryudisyidik.challengegoldchapter6.data.remote.response.MovieResponse
import com.binar.ariefaryudisyidik.challengegoldchapter6.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> = _movie

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "MainViewModel"
        const val API_KEY = "3e15ac9cb3114f2f303febd749ac0cf2"
    }

    init {
        fetchData()
    }

    private fun fetchData() {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getMovie(API_KEY)
        client.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _movie.postValue(response.body()?.results)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.postValue(false)
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}