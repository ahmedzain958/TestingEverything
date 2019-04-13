package com.hogp.testingeverything.room

import androidx.room.*
import com.hogp.testingeverything.room.model.User
import io.reactivex.Flowable

@Dao
interface UserDAO {
    @get:Query("select * from users")
    val allUsers: Flowable<List<User>>

    @Query("select * from users where id=:userId")
    fun getUserById(userId: Int): Flowable<User>

    @Insert
    fun insertUser(vararg users:User)

    @Update
    fun updateUser(vararg users:User)

    @Delete
    fun deleteUser(users:User)

    @Query ("Delete from users")
    fun deleteAllUsers()
}