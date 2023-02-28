package com.example.test.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.model.viewmodel
 * @class:       CustomViewModel
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2023/2/27 21:43
 * @Copyright (C) 2023 YSTEN
 * @author:       xuguangdong
 */
class CustomViewModel : ViewModel() {
    private val _countLiveData = MutableLiveData<Int>().apply {
        value = 0
    }

    /**
     * 对外提供的liveData
     */
    val countLiveData: LiveData<Int> = _countLiveData

    fun countPlus() {
        _countLiveData.value = _countLiveData.value?.plus(1)
    }
    fun clear() {
        _countLiveData.value = 0
    }
}