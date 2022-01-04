package com.androidDemo.contentProvider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?, version: Int)
    : SQLiteOpenHelper(context, "contentProvider.db", null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table person( _id integer primary key autoincrement, name varchar, age int)"
        db.execSQL(sql)
        db.execSQL("insert into person (name,age) values('TOM',11)")
        db.execSQL("insert into person (name,age) values('SUM',12)")
        db.execSQL("insert into person (name,age) values('GOD',13)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}