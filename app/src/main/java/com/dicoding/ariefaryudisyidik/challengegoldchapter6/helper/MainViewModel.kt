package com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserDataStoreManager) : ViewModel() {
    fun saveUsername(username: String) {
        viewModelScope.launch {
            pref.saveUsername(username)
        }
    }

    fun getUsername(): LiveData<String> {
        return pref.getUsername().asLiveData()
    }

    fun setLoggedInStatus(status: Boolean) {
        viewModelScope.launch {
            pref.setLoggedInStatus(true)
        }
    }

    fun getLoggedInStatus(): LiveData<Boolean> {
        return pref.getLoggedInStatus().asLiveData()
    }
}