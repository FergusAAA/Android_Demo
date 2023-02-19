package com.example.test.view.activity

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class ContentResolverActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var queryBtn: TextView
    lateinit var insertBtn: TextView
    lateinit var updateBtn: TextView
    lateinit var deleteBtn: TextView

    companion object {
        const val TAG = "ContentResolverActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)
        queryBtn = findViewById(R.id.btn_1)
        insertBtn = findViewById(R.id.btn_2)
        updateBtn = findViewById(R.id.btn_3)
        deleteBtn = findViewById(R.id.btn_4)

        queryBtn.setOnClickListener(this)
        insertBtn.setOnClickListener(this)
        updateBtn.setOnClickListener(this)
        deleteBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_1 -> query()
            R.id.btn_2 -> insert()
            R.id.btn_3 -> update()
            R.id.btn_4 -> delete()
        }
    }

    fun query() {
        //得到ContentProvider对象
        val contentResolver = contentResolver
        //创建uri
        val uri = Uri.parse("content://com.androiddemo.contentprovider.personprovider/person/1")
        //取出corsor中的数据
        val cursor = contentResolver.query(uri, null, null, null, null)
        while (cursor?.moveToNext() == true) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val age = cursor.getInt(cursor.getColumnIndex("age"))
            Log.w(TAG, "query: name: $name age: $age")
        }
        cursor?.close()
    }

    private fun insert() {
        //获得contentResolver对象
        val contentResolver = contentResolver
        //创建uri
        val uri = Uri.parse("content://com.androiddemo.contentprovider.personprovider/person/")
        val contentValues = ContentValues()
        contentValues.put("name", "good")
        contentValues.put("age", 11)
        val returnUri = contentResolver.insert(uri, contentValues)
        Log.w(TAG, "insert: returnUri: " + returnUri)
    }

    private fun delete() {
        //获得contentResolver对象
        val contentResolver = contentResolver
        //创建uri
        //根据id删除
//        val uri = Uri.parse("content://com.androiddemo.contentprovider.personprovider/person/1")
//        val deleteCount = contentResolver.delete(uri, null, null)

        val uri = Uri.parse("content://com.androiddemo.contentprovider.personprovider/person")
        val deleteCount = contentResolver.delete(uri, "name=?", arrayOf("帅哥"))
        Log.w(TAG, "update: deleteCount: " + deleteCount)
    }

    private fun update() {
        //获得contentResolver对象
        val contentResolver = contentResolver
        //参数
        val contentValues = ContentValues()
        contentValues.put("name","帅哥")
        contentValues.put("age", 10000)
        //创建uri
        //根据id更新
//        val uri = Uri.parse("content://com.androiddemo.contentprovider.personprovider/person/2")
//        contentResolver.update(uri, contentValues, null, null)
        val uri = Uri.parse("content://com.androiddemo.contentprovider.personprovider/person/")
        val updateCount = contentResolver.update(uri, contentValues, "name=?", arrayOf("good"))
        Log.w(TAG, "update: updateCount: " + updateCount)
    }
}