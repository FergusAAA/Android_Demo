package com.example.test.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.test.R
import com.example.test.database.AppDatabase
import com.example.test.database.UserDao
import com.example.test.model.bean.User
import com.example.test.presenter.DatabaseStoragePresenter
import com.example.test.view.imp.DatabaseStorageImp
import kotlin.concurrent.thread

class DatabaseStorageActivity : AppCompatActivity(), View.OnClickListener, DatabaseStorageImp {
    private lateinit var mCreateDbBtn: TextView
    private lateinit var mUpdateDbBtn: TextView
    private lateinit var mInsertBtn: TextView
    private lateinit var mUpdateBtn: TextView
    private lateinit var mDelBtn: TextView
    private lateinit var mQueryBtn: TextView
    private lateinit var mTestBtn: TextView

    private lateinit var testRoom: TextView

    /**
     * 使用room测试数据库功能
     */
    private lateinit var userDao: UserDao
    private lateinit var user1: User
    private lateinit var user2: User

    private var isTestRoom = false

    //数据库版本
    private var mDbVersion: Int = 1

    private var mPresenter: DatabaseStoragePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_storage)
        initView()
        initDao()
    }

    private fun initView() {
        mCreateDbBtn = findViewById(R.id.create_db)
        mUpdateDbBtn = findViewById(R.id.update_db)
        mInsertBtn = findViewById(R.id.insert)
        mUpdateBtn = findViewById(R.id.update)
        mDelBtn = findViewById(R.id.delete)
        mQueryBtn = findViewById(R.id.query)
        mTestBtn = findViewById(R.id.test_transaction)

        testRoom = findViewById(R.id.test_room)

        mCreateDbBtn.setOnClickListener(this)
        mUpdateDbBtn.setOnClickListener(this)
        mInsertBtn.setOnClickListener(this)
        mUpdateBtn.setOnClickListener(this)
        mDelBtn.setOnClickListener(this)
        mQueryBtn.setOnClickListener(this)
        mTestBtn.setOnClickListener(this)
        testRoom.setOnClickListener(this)

        mPresenter = DatabaseStoragePresenter(this)
    }

    fun initDao() {
        userDao = AppDatabase.getDatabase(this).userDao()
        user1 = User("Tom", "Brady", 10)
        user2 = User("Sam", "Hanks", 20)
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
        mCreateDbBtn.post {
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
            R.id.insert -> if (isTestRoom) {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            } else mPresenter?.insert(this, getValues())
            R.id.update -> if (isTestRoom) {
                user1.age = 30
                userDao.updateUser(user1)
            } else mPresenter?.updata(this, getValues(), arrayOf("1"))
            R.id.delete -> if (isTestRoom) userDao.deleteUserByLastName("Hanks") else mPresenter?.delete(
                this
            )
            R.id.query -> if (isTestRoom) {
                for (user in userDao.loadAllUsers()) {
                    Log.w(TAG, "onClick: $user")
                }
            } else mPresenter?.query(this, "name")
            R.id.test_room -> isTestRoom = true
        }
    }

    fun getValues(): ContentValues {
        val contentValues = ContentValues()
        contentValues.put("name", "sb")
        contentValues.put("age", 12)
        return contentValues
    }

    companion object {
        private const val TAG = "DatabaseStorageActivity"
    }
}