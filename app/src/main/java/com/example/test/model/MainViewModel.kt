package com.example.test.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.model
 * @class:       MainViewModel
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/8/13 20:47
 * @author:       xuguangdong
 */
class MainViewModel(count: Int) : ViewModel() {
    /**
     * 提供给外部使用的，实际上返回的是get方法的对象[countReal]
     *
     * 原本[count]字段定义为不可修改的[LiveData]
     */
    val count: LiveData<Int> get() = countReal

    /**
     * 真实使用的[LiveData]对象
     */
    private val countReal = MutableLiveData<Int>()

    init {
        countReal.value = count
    }

    fun plusOne() {
        val count = countReal.value ?: 0
        countReal.value = count + 1
    }
}