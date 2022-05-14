package com.example.test.view

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapter.ButtonAdapter
import com.example.test.adapter.ButtonAdapter.OnClickListener

/**
 * @title:       标题
 * @project:     AndroidDemo
 * @package:     com.example.test.view
 * @class:       PropertyActivity
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/3/3 11:39
 * @Copyright (C) 2022 YSTEN
 * @author:       NC0955
 */
class PropertyActivity : Activity() {
    private lateinit var mBtnRecyclerView: RecyclerView
    private lateinit var mAnimationImg: ImageView
    private lateinit var mButtonAdapter: ButtonAdapter
    private val mDataLists = arrayListOf<String>("123")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        initView()
        initData()
        initListener()
    }

    private fun initListener() {
        mButtonAdapter.onClickListener = OnClickListener { position ->
            when (mDataLists[position]) {
                HEARTBEAT_ANIMATION -> startHeartBeat()
            }
        }
    }

    private fun initData() {
        Log.w(TAG, "initData")
        mDataLists.add(HEARTBEAT_ANIMATION)
        mButtonAdapter.mDataList = mDataLists
    }

    private fun initView() {
        Log.w(TAG, "initView")
        mBtnRecyclerView = findViewById(R.id.btn_list)
        mAnimationImg = findViewById(R.id.animation_img)
        mButtonAdapter = ButtonAdapter(mDataLists, this)
        mBtnRecyclerView.adapter = mButtonAdapter
        mBtnRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    /**
     * 开始心跳动画
     */
    private fun startHeartBeat() {
        //设置View的中心
        mAnimationImg.post {
            mAnimationImg.pivotX = mAnimationImg.width / 2f
            mAnimationImg.pivotY = mAnimationImg.height.toFloat()
        }

        val animator = ValueAnimator.ofObject(TypeEvaluator<Float> { fraction, startValue, endValue ->
            if (mAnimationImg.hasFocus()) {
                return@TypeEvaluator startValue!! + fraction * (endValue!! - startValue) + 0.5f
            }
            startValue!! + fraction * (endValue!! - startValue)
        }, 1.0f, 1.12f, 1.0f, 1.0f, 1.0f, 1.0f)

        animator.addUpdateListener {
            mAnimationImg.scaleX = it.animatedValue as Float
            mAnimationImg.scaleY = it.animatedValue as Float
        }

        animator.duration = 2_000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }

    companion object {
        const val TAG = "PropertyActivity"
        const val HEARTBEAT_ANIMATION = "心跳动画"
    }
}