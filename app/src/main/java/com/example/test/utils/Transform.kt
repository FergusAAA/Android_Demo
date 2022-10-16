package com.example.test.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.utils
 * @class:       Transform
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/10/16 05:31
 * @Copyright (C) 2022 YSTEN
 * @author:       xuguangdong
 */

/**
 * 拓展 px 字段
 *
 * 将 dp 转换为 px
 */
val Int.px: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }