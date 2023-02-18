package com.example.test.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.px
import java.io.InputStream

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.view
 * @class:       TestView
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/10/16 05:00
 * @Copyright (C) 2022 YSTEN
 * @author:       xuguangdong
 */
class CustomTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val text =
        "2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日 2022年12月23日-2022年12月29日"

    val assetManager = context.assets

    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.textSize = 16.px.toFloat()
        this.textAlign = Paint.Align.LEFT
    }
    val imagePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val metrics = Paint.FontMetrics()

    private val IMAGE_WIDTH = 100.px
    private val IMAGE_PADDING = 50.px
    private var IMAGE_HIGHT = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmap = getBitmap(IMAGE_WIDTH)
        bitmap?.let {
            IMAGE_HIGHT = bitmap.height
            canvas.drawBitmap(
                it, width - IMAGE_WIDTH.toFloat(), IMAGE_PADDING.toFloat(), imagePaint
            )
        }

        textPaint.apply {
            this.getFontMetrics(metrics)
            val measuredWidth = floatArrayOf(0f)
            var start = 0
            var count: Int
            var dy = -metrics.top
            //换行绘制文字
            while (start < text.length) {
                //允许绘制文字的最大宽度
                val textWidth =
                    if (dy + metrics.bottom > IMAGE_PADDING && dy + metrics.top < IMAGE_PADDING + IMAGE_HIGHT) {
                        width - IMAGE_WIDTH
                    } else {
                        width
                    }
                //计算每行能绘制文字的个数
                count = this.breakText(
                    text,
                    start,
                    text.length,
                    true,
                    textWidth.toFloat(),
                    measuredWidth
                )
                canvas.drawText(text, start, start + count, 0f, dy, this)
                //换行后下一行开始的文字位置
                start += count
                //换行后下一行的垂直偏移
                dy += this.fontSpacing
            }
        }

    }

    fun getBitmap(width: Int): Bitmap? {
        var inputStream: InputStream? = null
        try {
            inputStream = assetManager.open("batman.jpg")
            val option = BitmapFactory.Options()
            option.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, option)
            inputStream.close()
            inputStream = assetManager.open("batman.jpg")
            option.inMutable = true
            option.inJustDecodeBounds = false
            option.inDensity = option.outWidth
            option.inTargetDensity = width
            return BitmapFactory.decodeStream(inputStream, null, option)
        } catch (e: Exception) {
            return null
        } finally {
            try {
                inputStream?.close()
            } catch (e: Exception) {
            }
        }
    }
}