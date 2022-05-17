package com.dicoding.ariefaryudisyidik.challengegoldchapter6.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserDataStoreManager) : ViewModel() {

    fun saveUser(id: Int, status: Boolean) {
        viewModelScope.launch {
            pref.saveUser(id, status)
        }
    }

    fun getId(): LiveData<Int> {
        return pref.getId().asLiveData()
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return pref.getLoginStatus().asLiveData()
    }

    fun logoutUser() {
        viewModelScope.launch {
            pref.logoutUser()
        }
    }
}