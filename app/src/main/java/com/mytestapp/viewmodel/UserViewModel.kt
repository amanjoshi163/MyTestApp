package com.mytestapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mytestapp.model.MovieListModel
import com.mytestapp.model.User
import com.mytestapp.roomdb.UserDatabase

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var userList: MutableLiveData<List<User>>
    private val context = getApplication<Application>().applicationContext
    private var appDatabase: UserDatabase? = null
    private lateinit var listUsers: List<User>

    fun getUsers(): MutableLiveData<List<User>> {
        appDatabase = UserDatabase.getInstance(context)
        listUsers = ArrayList()
        userList = MutableLiveData()
        loadUsers()


        return userList
    }

    private fun loadUsers() {
        listUsers = appDatabase!!.userDao().getAllUser()
        if (listUsers.isNotEmpty()){
            userList.value = listUsers
        }else{
            userList.value = null
        }
    }
}