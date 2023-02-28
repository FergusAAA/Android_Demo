package com.example.test.view.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.example.test.utils.dp

/**
 * @title:       标题
 * @project:     Android_Demo
 * @package:     com.example.test.view
 * @class:       DashboardView
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2023/2/18 18:18
 * @author:       xuguangdong
 */
/**
 * 圆弧底开角
 */
const val OPEN_ANGLE = 120

/**
 * 竖线宽度和高度
 */
val DASH_WIDTH = 2f.dp
val DASH_LENGTH = 20f.dp

class DashboardView(context: Context?, attr: AttributeSet?) : View(context, attr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 效果
     */
    private lateinit var pathDashPathEffect: PathDashPathEffect

    /**
     * 刻度
     */
    private val dash = Path()

    /**
     * 线
     */
    private val line = Path()

    init {
        paint.strokeWidth = 3f.dp
        paint.style = Paint.Style.STROKE
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        line.reset()
        line.addArc(
            width / 2f - 150.dp,
            height / 2f - 150.dp,
            width / 2f + 150.dp,
            height / 2f + 150.dp,
            90f + OPEN_ANGLE / 2,
            360f - OPEN_ANGLE,
        )
        val pathMeasure = PathMeasure(line, false)
        val advance = (pathMeasure.length -  DASH_WIDTH) / 19
        pathDashPathEffect = PathDashPathEffect(dash, advance, 0f, PathDashPathEffect.Style.ROTATE)
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    override fun onDraw(canvas: Canvas) {
        //画线
        canvas.drawPath(
            line,
            paint
        )
        //设置虚线效果
        paint.pathEffect = pathDashPathEffect
        //画刻度
        canvas.drawPath(
            line,
            paint
        )
        paint.pathEffect = null
    }
}