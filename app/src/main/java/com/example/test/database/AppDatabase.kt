package com.example.test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.model.bean.User

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.database
 * @class:       AppDatabase
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/9/24 15:55
 * @Copyright (C) 2022 YSTEN
 * @author:       xuguangdong
 */
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .allowMainThreadQueries()
                .build().apply {
                instance = this
            }
        }
    }
}