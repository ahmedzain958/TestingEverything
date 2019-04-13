package com.hogp.testingeverything.room.database

import android.content.Context
import androidx.room.Room
import com.hogp.testingeverything.room.model.User
import io.reactivex.Flowable

class UserRepository(private val mLocationDataSource: IUserDataSource) : IUserDataSource {
    companion object {
        private var mInstance: UserRepository? = null

        fun getInstance(mLocationDataSource: IUserDataSource): UserRepository {
            if (mInstance == null) {
                mInstance = UserRepository(mLocationDataSource = mLocationDataSource)
            }
            return mInstance!!
        }
    }

    override val allUsers: Flowable<List<User>>
        get() = mLocationDataSource.allUsers

    override fun getUseById(userId: Int): Flowable<User> {
        return mLocationDataSource.getUseById(userId)
    }

    override fun insertUser(vararg users: User) {
        return mLocationDataSource.insertUser(*users)
    }

    override fun updateUser(vararg users: User) {
        return mLocationDataSource.updateUser(*users)
    }

    override fun deleteUser(users: User) {
        return mLocationDataSource.deleteUser(users)
    }

    override fun deleteAllUsers() {
        return mLocationDataSource.deleteAllUsers()

    }
}