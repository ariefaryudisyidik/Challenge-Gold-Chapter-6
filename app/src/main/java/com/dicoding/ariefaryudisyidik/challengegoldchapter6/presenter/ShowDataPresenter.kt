package com.dicoding.ariefaryudisyidik.challengegoldchapter6.presenter

import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.remote.response.MovieResponse
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.remote.retrofit.ApiConfig
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.viewmodel.MovieViewModel
import retrofit2.Call
import retrofit2.Response

class ShowDataPresenter(private var viewInterface: ShowDataContract.ViewInterface) :
    ShowDataContract.PresenterInterface {

    override fun showData() {
        val client = ApiConfig.getApiService().getMovie(MovieViewModel.API_KEY)
        client.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    viewInterface.showResult(body)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                viewInterface.showError(t.localizedMessage)
            }
        })
    }
}