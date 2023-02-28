package com.example.test.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * 拓展 px 字段
 *
 * 将 dp 转换为 px
 */
val Float.dp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toFloat()
    }