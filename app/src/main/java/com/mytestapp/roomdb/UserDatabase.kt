package com.mytestapp.roomdb

import androidx.room.Database
import com.mytestapp.model.User
import android.content.Context
 import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): UserDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, UserDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }

//        private val roomCallback = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                populateDatabase(instance!!)
//            }
//        }
//
//        private fun populateDatabase(db: UserDatabase) {
//            val noteDao = db.noteDao()
//            subscribeOnBackground {
//                noteDao.insert(Note("title 1", "desc 1", 1))
//                noteDao.insert(Note("title 2", "desc 2", 2))
//                noteDao.insert(Note("title 3", "desc 3", 3))
//
//            }
//        }
//    }

    }

}