package com.example.test.model

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.math.log

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.model
 * @class:       MyObserver
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/9/17 16:24
 * @Copyright (C) 2022 YSTEN
 * @author:       xuguangdong
 */
class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {
    companion object {
        private const val TAG = "MyObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.w(TAG, "activityStart: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.w(TAG, "activityStop: ")
    }
}