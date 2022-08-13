package com.example.test.view

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.ButtonAdapter
import com.example.test.model.Constant
import com.example.test.model.MainViewModel

class MainActivity : FragmentActivity() {
    private lateinit var mBtnRecyclerView: RecyclerView

    /**
     * 点击按钮的总次数
     */
    private lateinit var clickCountTextView: TextView

    /**
     * 当前界面的ViewModel
     */
    private lateinit var mainViewModel: MainViewModel
    private val mDataList = arrayListOf(
        Constant.补间动画,
        Constant.属性动画,
        Constant.内部文件存储,
        Constant.外部文件存储,
        Constant.数据库存储,
        Constant.CONTENT_PROVIDER,
        Constant.SERVICE_TEST
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnRecyclerView = findViewById(R.id.btn_recyclerview)
        clickCountTextView = findViewById(R.id.click_count)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mBtnRecyclerView.adapter = ButtonAdapter(mDataList, this)
        mBtnRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mBtnRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.bottom = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    5f,
                    resources.displayMetrics
                ).toInt()
            }
        })
        (mBtnRecyclerView.adapter as ButtonAdapter).onClickListener =
            ButtonAdapter.OnClickListener {
                onClick(mDataList[it])
            }
        refreshCount()
    }

    private fun onClick(buttonName: String) {
        mainViewModel.count++
        refreshCount()
        when (buttonName) {
            Constant.补间动画 -> startActivity(TweenActivity::class.java)
            Constant.内部文件存储 -> startActivity(InnerFirestoneActivity::class.java)
            Constant.外部文件存储 -> startActivity(ExternalStorageActivity::class.java)
            Constant.数据库存储 -> startActivity(DatabaseStorageActivity::class.java)
            Constant.CONTENT_PROVIDER -> startActivity(ContentResolverActivity::class.java)
            Constant.属性动画 -> startActivity(PropertyActivity::class.java)
            Constant.SERVICE_TEST -> startActivity(ServiceTest::class.java)
        }
    }

    private fun refreshCount() {
        clickCountTextView.text =
            resources.getString(R.string.current_click_count, mainViewModel.count)
    }

    private fun startActivity(targetCls: Class<*>) {
        val intent = Intent(this, targetCls)
        startActivity(intent)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}