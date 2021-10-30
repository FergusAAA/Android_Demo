package com.example.test.presenter.imp

import android.content.Context

interface DatabaseStoragePresenterImp {
    fun createDb(context: Context?, version: Int)
    fun updateDb(context: Context,versionNum: Int)
}