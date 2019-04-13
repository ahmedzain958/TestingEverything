package com.hogp.testingeverything.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hogp.testingeverything.room.UserDAO
import com.hogp.testingeverything.room.database.UserDatabase.Companion.DATABASE_VERSION
import com.hogp.testingeverything.room.model.User

@Database(entities = arrayOf(User::class), version = DATABASE_VERSION)
abstract class UserDatabase() : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        const val DATABASE_VERSION = 1

        val DATABASE_NAME = "EDMT-Database-Room"

        private var mInstance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration().build()
            }
            return mInstance!!
        }
    }
}