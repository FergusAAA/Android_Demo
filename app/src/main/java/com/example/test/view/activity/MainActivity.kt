package com.example.test.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import androidx.core.content.edit
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.ButtonAdapter
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.Constant
import com.example.test.model.MainViewModel
import com.example.test.model.MainViewModelFactory
import com.example.test.model.MyObserver
import com.example.test.view.ServiceTest

class MainActivity : FragmentActivity() {
    /**
     * 当前界面的ViewModel
     */
    private lateinit var mainViewModel: MainViewModel

    /**
     * sp
     */
    private lateinit var sp: SharedPreferences

    private lateinit var myObserver: MyObserver

    private lateinit var binding: ActivityMainBinding

    private val mDataList = arrayListOf(
        Constant.补间动画,
        Constant.属性动画,
        Constant.内部文件存储,
        Constant.外部文件存储,
        Constant.数据库存储,
        Constant.CONTENT_PROVIDER,
        Constant.SERVICE_TEST,
        Constant.自定义View
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initViewModel()
        initLifecycle()
    }

    private fun initLifecycle() {
        myObserver = MyObserver(lifecycle)
        lifecycle.addObserver(myObserver)
    }

    override fun onPause() {
        super.onPause()
        sp.edit { putInt(COUNT, mainViewModel.count.value ?: 0) }
    }

    private fun onClick(buttonName: String) {
        mainViewModel.plusOne()
        when (buttonName) {
            Constant.补间动画 -> startActivity(TweenActivity::class.java)
            Constant.内部文件存储 -> startActivity(InnerFirestoneActivity::class.java)
            Constant.外部文件存储 -> startActivity(ExternalStorageActivity::class.java)
            Constant.数据库存储 -> startActivity(DatabaseStorageActivity::class.java)
            Constant.CONTENT_PROVIDER -> startActivity(ContentResolverActivity::class.java)
            Constant.属性动画 -> startActivity(PropertyActivity::class.java)
            Constant.SERVICE_TEST -> startActivity(ServiceTest::class.java)
            Constant.自定义View -> startActivity(CustomViewActivity::class.java)
        }
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        sp = getPreferences(Context.MODE_PRIVATE)
        val count = sp.getInt("count", 0)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(count))[MainViewModel::class.java]
        mainViewModel.count.observe(this) {
            refreshCount(it)
        }
    }

    private fun initView() {
        binding.btnRecyclerview.adapter = ButtonAdapter(mDataList, this)
        binding.btnRecyclerview.layoutManager = GridLayoutManager(this, 2)

        binding.btnRecyclerview.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.bottom = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    5f,
                    resources.displayMetrics
                ).toInt()
            }
        })
        (binding.btnRecyclerview.adapter as ButtonAdapter).onClickListener =
            ButtonAdapter.OnClickListener {
                onClick(mDataList[it])
            }
    }

    private fun refreshCount(count: Int) {
        binding.clickCount.text =
            resources.getString(R.string.current_click_count, count)
    }

    private fun startActivity(targetCls: Class<*>) {
        val intent = Intent(this, targetCls)
        startActivity(intent)
    }

    companion object {
        const val TAG = "MainActivity"
        const val COUNT = "count"
    }
}