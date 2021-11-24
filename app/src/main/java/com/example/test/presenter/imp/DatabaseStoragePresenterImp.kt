package com.example.test.presenter.imp

import android.content.ContentValues
import android.content.Context

interface DatabaseStoragePresenterImp {
    fun createDb(context: Context?, version: Int)
    fun updateDb(context: Context,versionNum: Int)
    fun insert(context: Context, values: ContentValues)
    fun updata(context: Context,values:ContentValues,ids:Array<String>)
    fun delete(context: Context)
    fun query(context: Context, column: String)
}