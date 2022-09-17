package com.example.test.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.model
 * @class:       MainViewModelFactory
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/9/17 15:18
 * @Copyright (C) 2022 YSTEN
 * @author:       xuguangdong
 */
class MainViewModelFactory(private val count: Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(count) as T
    }
}