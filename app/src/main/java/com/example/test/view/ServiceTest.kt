package com.example.test.view

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Rect
import android.os.Bundle
import android.os.IBinder
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.ButtonAdapter
import com.example.test.model.Constant
import com.example.test.service.ForegroundService
import com.example.test.service.MyService

class ServiceTest : Activity() {
    private lateinit var mBtnRecyclerView: RecyclerView
    private val mDataList = arrayListOf(Constant.SERVICE_START, Constant.SERVICE_STOP,
            Constant.BIND_SERVICE, Constant.UNBIND_SERVICE, Constant.FOREGROUND_SERVICE,
            Constant.STOP_FOREGROUND_SERVICE)

    private lateinit var mConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_test)
        mBtnRecyclerView = findViewById(R.id.btn_recyclerview)
        mBtnRecyclerView.adapter = ButtonAdapter(mDataList, this)
        mBtnRecyclerView.layoutManager = LinearLayoutManager(this)
        mBtnRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()
            }
        })
        (mBtnRecyclerView.adapter as ButtonAdapter).onClickListener = ButtonAdapter.OnClickListener {
            onClick(mDataList[it])
        }
        //Service连接的回调
        mConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val downloadBinder = service as MyService.DownloadBinder
                downloadBinder.StartDownload()
                downloadBinder.getProgress()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }
        }
    }

    fun onClick(buttonName: String) {
        when (buttonName) {
            Constant.SERVICE_START -> startService(MyService::class.java)
            Constant.SERVICE_STOP -> stopService(MyService::class.java)
            Constant.BIND_SERVICE -> bindService(MyService::class.java)
            Constant.UNBIND_SERVICE -> unbindService(mConnection)
            Constant.FOREGROUND_SERVICE -> startService(ForegroundService::class.java)
            Constant.STOP_FOREGROUND_SERVICE -> stopService(ForegroundService::class.java)
        }
    }

    private fun startService(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startService(intent)
    }

    private fun stopService(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        stopService(intent)
    }

    private fun bindService(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        bindService(intent, mConnection, BIND_AUTO_CREATE)
    }
}