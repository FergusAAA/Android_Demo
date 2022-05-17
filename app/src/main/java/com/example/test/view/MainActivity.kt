package com.example.test.view

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.ButtonAdapter
import com.example.test.model.Constant

class MainActivity : Activity() {
    private lateinit var mBtnRecyclerView: RecyclerView
    private val mDataList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnRecyclerView = findViewById(R.id.btn_recyclerview)
        mBtnRecyclerView.adapter = ButtonAdapter(mDataList, this)
        mBtnRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mBtnRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.bottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()
            }
        })
        (mBtnRecyclerView.adapter as ButtonAdapter).onClickListener = ButtonAdapter.OnClickListener {
            onClick(mDataList[it])
        }
        mDataList.addAll(Constant.MAIN_ACTIVITY_BTN_LIST.split("|"))
    }

    private fun onClick(buttonName: String) {
        when (buttonName) {
            "补间动画" -> startActivity(TweenActivity::class.java)
            "内部文件存储" -> startActivity(InnerFirestoneActivity::class.java)
            "外部文件存储" -> startActivity(ExternalStorageActivity::class.java)
            "数据库存储" -> startActivity(DatabaseStorageActivity::class.java)
            "CONTENT PROVIDER" -> startActivity(ContentResolverActivity::class.java)
            "属性动画" -> startActivity(PropertyActivity::class.java)
        }
    }

    private fun startActivity(targetCls: Class<*>) {
        val intent = Intent(this, targetCls)
        startActivity(intent)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}