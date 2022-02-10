package com.example.test.fragment

import android.view.KeyEvent
import androidx.fragment.app.Fragment

/**
 * @title:       标题
 * @project:     AndroidDemo
 * @package:     com.example.test.fragment
 * @class:       BaseFragment
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/1/5 15:35
 * @Copyright (C) 2022 YSTEN
 * @author:       NC0955
 */
open class BaseFragment : Fragment() {
    fun dispatchKeyEvent(event: KeyEvent?):Boolean{
       return false
    }
}