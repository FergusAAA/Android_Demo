package com.example.test.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.utils
 * @class:       ResourceExtend
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2023/2/27 21:10
 * @Copyright (C) 2023 YSTEN
 * @author:       xuguangdong
 */
fun Resources.getBitmapById(id: Int, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(this, id, options)
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeResource(this, id, options)
}