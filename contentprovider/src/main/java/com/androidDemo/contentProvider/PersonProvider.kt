package com.androidDemo.contentProvider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import com.androidDemo.contentProvider.constant.Constant
import com.google.android.material.internal.ContextUtils

/**
 * 操作person表的provider
 */
class PersonProvider() : ContentProvider() {
    private var dbHelper: DBHelper? = null
    private var dataBase: SQLiteDatabase? = null

    init {
        Log.w(TAG, "PersonProvider()")
    }

    override fun onCreate(): Boolean {
        Log.w(TAG, "onCreate: ")
        dbHelper = DBHelper(context, 1)
        dataBase = dbHelper?.readableDatabase
        return false
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {

        val cursor: Cursor?
        Log.w(TAG, "query: ")
        //匹配uri，返回code
        val code = matcher.match(uri)
        if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_ONE) {
            cursor = dataBase?.query("person", projection, selection, selectionArgs, null, null, null)
        } else if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_TWO) {
            //获取id
            val id = ContentUris.parseId(uri)
            cursor = dataBase?.query("person", projection, "_id=?",
                    arrayOf(id.toString()), null, null, null)
        } else {
            throw Exception("uri 不合法")
        }
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.w(TAG, "insert: ")
        val dataBase = dbHelper?.readableDatabase
        val code = matcher.match(uri)
        if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_ONE) {
            val id: Long? = dataBase?.insert("person", null, values)
            //将 id 添加到 uri 中
            if (id != null) {
                return ContentUris.withAppendedId(uri, id)
            }
        } else {
            throw Exception("uri 不合法")
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.w(TAG, "delete: ")
        val dataBase = dbHelper?.readableDatabase
        val code = matcher.match(uri)
        var deleteCount = -1
        if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_ONE) {
            deleteCount = dataBase?.delete("person", selection, selectionArgs)!!
        } else if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_TWO) {
            val id = ContentUris.parseId(uri)
            deleteCount = dataBase?.delete("person", "_id=?", arrayOf(id.toString()))!!
        } else {
            throw Exception("uri 不合法")
        }
        return deleteCount
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.w(TAG, "update: ")
        val dataBase = dbHelper?.readableDatabase
        val code = matcher.match(uri)
        var updateCount = -1
        if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_ONE) {
            updateCount = dataBase?.update("person", values, selection, selectionArgs)!!
        } else if (code == Constant.CONTENT_PROVIDER_MATCH_CODE_TWO) {
            val id = ContentUris.parseId(uri)
            updateCount = dataBase?.update("person", values, "_id=?", arrayOf(id.toString()))!!
        } else {
            throw Exception("uri 不合法")
        }
        return updateCount
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "PersonProvider"

        //用来存放所有合法的uri的容器
        private val matcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            //不根据id查询
            matcher.addURI("com.androiddemo.contentprovider.personprovider",
                    "/person", Constant.CONTENT_PROVIDER_MATCH_CODE_ONE)
            //根据id查询
            matcher.addURI("com.androiddemo.contentprovider.personprovider",
                    "/person/#", Constant.CONTENT_PROVIDER_MATCH_CODE_TWO)  //#号匹配任意数字
        }
    }
}