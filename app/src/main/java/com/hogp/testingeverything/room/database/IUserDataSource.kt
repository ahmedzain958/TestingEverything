package com.hogp.testingeverything.room.database

import com.hogp.testingeverything.room.model.User
import io.reactivex.Flowable

interface IUserDataSource{
    val allUsers:Flowable<List<User>>
    fun getUseById(userId:Int):Flowable<User>
    fun insertUser(vararg users:User)
    fun updateUser(vararg users:User)
    fun deleteUser(users:User)
    fun deleteAllUsers()
}