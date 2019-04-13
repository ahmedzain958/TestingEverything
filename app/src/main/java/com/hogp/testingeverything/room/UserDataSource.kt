package com.hogp.testingeverything.room

import android.content.Context
import androidx.room.Room
import com.hogp.testingeverything.room.database.IUserDataSource
import com.hogp.testingeverything.room.database.UserDatabase
import com.hogp.testingeverything.room.model.User
import io.reactivex.Flowable

class UserDataSource(private val userDAo: UserDAO) : IUserDataSource {

    companion object {
        private var mInstance: UserDataSource? = null

        fun getInstance(userDAo: UserDAO): UserDataSource {
            if (mInstance == null) {
                mInstance = UserDataSource(userDAo = userDAo)
            }
            return mInstance!!
        }
    }

    override val allUsers: Flowable<List<User>>
        get() = userDAo.allUsers

    override fun getUseById(userId: Int): Flowable<User> {
        return userDAo.getUserById(userId)
    }

    override fun insertUser(vararg users: User) {
        userDAo.insertUser(*users)
    }

    override fun updateUser(vararg users: User) {
        userDAo.updateUser(*users)
    }

    override fun deleteUser(users: User) {
        userDAo.deleteUser(users)
    }

    override fun deleteAllUsers() {
        userDAo.deleteAllUsers()
    }
}