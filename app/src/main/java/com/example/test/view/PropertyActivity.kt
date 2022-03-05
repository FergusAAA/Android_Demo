package com.example.test.view

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

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
    private lateinit var mBtnList: RecyclerView
    private lateinit var mAnimationImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        initView()
    }

    private fun initView() {
        mBtnList = findViewById(R.id.btn_list)
        mAnimationImg = findViewById(R.id.animation_img)
        val linearLayoutManager = LinearLayoutManager(this)

    }
}