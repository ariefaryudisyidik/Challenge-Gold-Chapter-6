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

    fun update(user: User) {
        viewModelScope.launch {
            repository.update(user)
        }
    }

    fun getUser(id: Int): User {
        return repository.getUser(id)
    }

    fun checkUser(email: String, password: String): User {
        return repository.checkUser(email, password)
    }
}