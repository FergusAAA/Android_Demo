package com.example.test.presenter

import android.content.Context
import android.widget.Toast
import com.example.test.presenter.imp.DatabaseStoragePresenterImp
import com.example.test.utils.DBHelper
import com.example.test.view.imp.DatabaseStorageImp

class DatabaseStoragePresenter(val mView: DatabaseStorageImp) : DatabaseStoragePresenterImp {
    private var mVersion = 1

    override fun createDb(context: Context?, version: Int) {
        if (version > 1) {
            mView.createDbFailure("当前数据库已创建")
            return
        }
        val dbHelper = DBHelper(context, version)
        dbHelper.readableDatabase
        mView.createDbSuccess()
    }

    override fun updateDb(context: Context, versionNum: Int) {
        mVersion = if (versionNum > mVersion) {
            val dbHelper = DBHelper(context, versionNum)
            dbHelper.readableDatabase
            mView.updateDbSuccess()
            versionNum
        } else {
            mVersion
        }
    }
}