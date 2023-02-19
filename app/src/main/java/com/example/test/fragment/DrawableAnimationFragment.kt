package com.example.test.fragment

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R
import kotlinx.android.synthetic.main.activity_innerfilestorage.view.*

/**
 * @title:       标题
 * @project:     AndroidDemo
 * @package:     com.example.test.fragment
 * @class:       DrawableAnimationFragment
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/1/4 17:01
 * @author:       NC0955
 */
class DrawableAnimationFragment : BaseFragment(), View.OnClickListener {
    private lateinit var button: TextView
    private lateinit var mView: View
    private lateinit var animation: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_drawabel_animation, null, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initListener()
    }

    private fun initListener() {
        button.setOnClickListener(this)
    }

    private fun initView() {
        button = mView.findViewById(R.id.button)
        animation = mView.findViewById(R.id.drawable_animation)
        button.requestFocus()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> startAnimation()
        }
    }

    fun startAnimation() {
        animation.animate()
        val animationDrawable: AnimationDrawable = animation.background as AnimationDrawable
        when (animationDrawable.isRunning) {
            true -> animationDrawable.stop()
            false -> animationDrawable.start()
        }
    }
}