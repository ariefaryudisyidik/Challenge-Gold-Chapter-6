package com.dicoding.ariefaryudisyidik.challengegoldchapter6.data.local

class UserRepository(private val userDao: UserDao) {
    fun insert(user: User) {
        userDao.insert(user)
    }

    fun checkUser(email: String, password: String) {
        userDao.checkUser(email, password)
    }
}