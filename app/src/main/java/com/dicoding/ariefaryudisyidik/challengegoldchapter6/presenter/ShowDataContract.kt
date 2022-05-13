package com.dicoding.ariefaryudisyidik.challengegoldchapter6.presenter

import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.remote.response.MovieResponse

class ShowDataContract {

    interface PresenterInterface {
        fun showData()
    }

    interface ViewInterface {
        fun showResult(result: MovieResponse)
        fun showError(errorMessage: String)
    }
}