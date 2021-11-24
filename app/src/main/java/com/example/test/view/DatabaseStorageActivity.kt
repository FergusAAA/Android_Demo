package com.example.test.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.test.R
import com.example.test.presenter.DatabaseStoragePresenter
import com.example.test.view.imp.DatabaseStorageImp

class DatabaseStorageActivity : AppCompatActivity(), View.OnClickListener, DatabaseStorageImp {
    private var mCreateDbBtn: TextView? = null
    private var mUpdateDbBtn: TextView? = null
    private var mInsertBtn: TextView? = null
    private var mUpdateBtn: TextView? = null
    private var mDelBtn: TextView? = null
    private var mQueryBtn: TextView? = null
    private var mTestBtn: TextView? = null

    //数据库版本
    private var mDbVersion: Int = 1

    private var mPresenter: DatabaseStoragePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_storage)
        initView()
    }

    private fun initView() {
        mCreateDbBtn = findViewById(R.id.create_db)
        mUpdateDbBtn = findViewById(R.id.update_db)
        mInsertBtn = findViewById(R.id.insert)
        mUpdateBtn = findViewById(R.id.update)
        mDelBtn = findViewById(R.id.delete)
        mQueryBtn = findViewById(R.id.query)
        mTestBtn = findViewById(R.id.test_transaction)

        mCreateDbBtn?.setOnClickListener(this)
        mUpdateDbBtn?.setOnClickListener(this)
        mInsertBtn?.setOnClickListener(this)
        mUpdateBtn?.setOnClickListener(this)
        mDelBtn?.setOnClickListener(this)
        mQueryBtn?.setOnClickListener(this)
        mTestBtn?.setOnClickListener(this)

        mPresenter = DatabaseStoragePresenter(this)
    }

    override fun createDbSuccess() {
        Toast.makeText(this, "数据库创建成功", Toast.LENGTH_SHORT).show()
    }

    override fun createDbFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun updateDbSuccess() {
        Toast.makeText(this, "更新数据库", Toast.LENGTH_SHORT).show()
    }

    override fun updateDbFailure() {
        TODO("Not yet implemented")
    }

    override fun insertDbSuccess() {
        mCreateDbBtn?.post {
            Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show()
        }
    }

    override fun insertDbFailure() {
        Toast.makeText(this, "插入数据失败", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.create_db -> mPresenter?.createDb(this, mDbVersion)
            R.id.update_db -> mPresenter?.updateDb(this, ++mDbVersion)
            R.id.insert -> mPresenter?.insert(this, getValues())
            R.id.update -> mPresenter?.updata(this, getValues(), arrayOf("1"))
            R.id.delete -> mPresenter?.delete(this)
            R.id.query -> mPresenter?.query(this, "name")
        }
    }

    fun getValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("name", "sb")
        contentValues.put("age", 12)
        return contentValues
    }
}