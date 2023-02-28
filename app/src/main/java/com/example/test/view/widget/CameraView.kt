package com.example.test.view.widget

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.test.R
import com.example.test.utils.dp
import com.example.test.utils.getBitmapById

/**
 * @title:       标题
 * @package:     com.example.test.view.widget
 * @class:       CameraView
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2023/2/22 21:01
 * @author:       xuguangdong
 */
class CameraView(context: Context, attr: AttributeSet) : View(context, attr) {
    private val BITMAP_WIDTH = 200.dp
    private val BITMAP_PADDING = 100.dp
    private val bitmap = resources.getBitmapById(R.drawable.head, BITMAP_WIDTH)
    private val paint = Paint()
    private val camera = Camera()

    init {
        //将camera沿着x轴旋转30度
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -10 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(
            BITMAP_PADDING + BITMAP_WIDTH / 2f,
            BITMAP_PADDING + BITMAP_WIDTH / 2f
        )
        camera.applyToCanvas(canvas)
        canvas.translate(
            -(BITMAP_PADDING + BITMAP_WIDTH / 2f),
            -(BITMAP_PADDING + BITMAP_WIDTH / 2f)
        )
        canvas.drawBitmap(bitmap, BITMAP_PADDING.toFloat(), BITMAP_PADDING.toFloat(), paint)
    }
}