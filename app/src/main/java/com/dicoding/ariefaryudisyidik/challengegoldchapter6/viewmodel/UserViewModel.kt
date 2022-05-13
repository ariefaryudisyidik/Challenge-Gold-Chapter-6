package com.dicoding.ariefaryudisyidik.challengegoldchapter6.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local.User
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local.UserRepository
import com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local.UserRoomDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserRoomDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }

    fun checkUser(email: String, password: String) {
        viewModelScope.launch {
            repository.checkUser(email, password)
        }
    }
}