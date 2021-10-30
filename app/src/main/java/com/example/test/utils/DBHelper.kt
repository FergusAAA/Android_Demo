package com.example.test.utils

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.test.utils.DBHelper

class DBHelper(context: Context?, version: Int) : SQLiteOpenHelper(context, "test.db", null, version) {
    /**
     * 当数据库文件创建的时候执行
     * 建表
     * 插入一些初始化数据
     *
     * @param db 数据库文件
     */
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table person( _id integer primary key autoincrement, name varchar, age int)"
        db.execSQL(sql)
        db.execSQL("insert into person (name,age) values('TOM',11)")
        db.execSQL("insert into person (name,age) values('SUM',12)")
        db.execSQL("insert into person (name,age) values('GOD',13)")
    }

    /**
     * 当传入的数据库版本大于当前数据库的版本时调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(TAG, "onUpgrade: oldVersion: $oldVersion newVersion: $newVersion")
    }

    companion object {
        private const val TAG = "DBHelper"
    }
}