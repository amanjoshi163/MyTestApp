package com.mytestapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mytestapp.model.User

@Dao
interface UserDAO {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM user_table where id LIKE  :id")
    fun findById(id: Int?): User?

}