package com.example.test.presenter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.test.presenter.imp.DatabaseStoragePresenterImp
import com.example.test.utils.DBHelper
import com.example.test.view.imp.DatabaseStorageImp
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DatabaseStoragePresenter(private val mView: DatabaseStorageImp) : DatabaseStoragePresenterImp {
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

    override fun insert(context: Context, values: ContentValues) {
        val runnable = Runnable {
            var database: SQLiteDatabase? = null
            try {
                val dbHelper = DBHelper(context, 3)
                database = dbHelper.readableDatabase
                //设置sql事务，开启事务
                database?.beginTransaction()
                val id = database?.insert("person", null, values)
                if (id == -1L) {
                    mView.insertDbFailure()
                } else {
                    mView.insertDbSuccess()
                    //设置事务成功
                    database?.setTransactionSuccessful()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //关闭事务
                database?.endTransaction()
                database?.close()
            }
        }
        var subscribe = Observable
                .just(runnable)
                .subscribeOn(Schedulers.io())
                .subscribe({ run: Runnable -> run.run() }, { t: Throwable? -> t?.printStackTrace() })

    }

    override fun updata(context: Context, values: ContentValues, ids: Array<String>) {
        var readableDatabase: SQLiteDatabase? = null
        try {
            val dbHelper = DBHelper(context, 3)
            readableDatabase = dbHelper.readableDatabase
            //设置sql事务，开启事务
            readableDatabase?.beginTransaction()
            val id = readableDatabase?.update("person", values, "_id=?", ids)
            //设置事务成功
            readableDatabase?.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            //关闭事务
            readableDatabase?.endTransaction()
            readableDatabase?.close()
        }
    }

    override fun delete(context: Context) {
        val dbHelper = DBHelper(context, 3)
        val readableDatabase = dbHelper.readableDatabase
        val id = readableDatabase.delete("person", "_id=4", null)
        readableDatabase.close()
    }

    override fun query(context: Context, column: String) {
        var readableDatabase: SQLiteDatabase? = null
        try {
            val dbHelper = DBHelper(context, 3)
            readableDatabase = dbHelper.readableDatabase
            //开启事务
            readableDatabase?.beginTransaction()
            //select * from person
            //val cursor: Cursor = readableDatabase.query("person", null, null, null, null, null, null)
            val cursor: Cursor? = readableDatabase?.query("person", null, "_id=?"
                    , arrayOf("3"), null, null, null)
            //设置事务成功
            readableDatabase?.setTransactionSuccessful()

            //取出Cursor 中所有的数据
            while (cursor?.moveToNext() == true) {
                val id = cursor.getInt(cursor.getColumnIndex("_id"))
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val age = cursor.getString(cursor.getColumnIndex("age"))
                Toast.makeText(context, "id: $id name: $name age: $age", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            //关闭事务
            readableDatabase?.endTransaction()
            readableDatabase?.close()
        }
    }
}