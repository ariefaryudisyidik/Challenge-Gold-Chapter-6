package com.binar.ariefaryudisyidik.challengegoldchapter6.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.binar.ariefaryudisyidik.challengegoldchapter6.helper.UserDataStoreManager
import kotlinx.coroutines.launch

class UserViewModel(private val pref: UserDataStoreManager) : ViewModel() {

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