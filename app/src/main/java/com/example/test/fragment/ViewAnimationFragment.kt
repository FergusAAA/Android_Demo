package com.example.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import com.example.test.R

class ViewAnimationFragment : BaseFragment(), View.OnClickListener {

    private var mView: View? = null
    private var scaleAnimationCode: TextView? = null
    private var scaleAnimationXml: TextView? = null
    private var rotateAnimationCode: TextView? = null
    private var rotateAnimationXml: TextView? = null
    private var alphaAnimationCode: TextView? = null
    private var alphaAnimationXml: TextView? = null
    private var tranlateAnimationCode: TextView? = null
    private var tranlateAnimationXml: TextView? = null
    private var setAnimationCode: TextView? = null
    private var setAnimationXml: TextView? = null
    private var testImg: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_view_animation, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initListener()
    }

    private fun initListener() {
        scaleAnimationCode?.setOnClickListener(this)
        scaleAnimationXml?.setOnClickListener(this)
        rotateAnimationCode?.setOnClickListener(this)
        rotateAnimationXml?.setOnClickListener(this)
        alphaAnimationCode?.setOnClickListener(this)
        alphaAnimationXml?.setOnClickListener(this)
        tranlateAnimationCode?.setOnClickListener(this)
        tranlateAnimationXml?.setOnClickListener(this)
        setAnimationCode?.setOnClickListener(this)
        setAnimationXml?.setOnClickListener(this)
    }

    private fun initView() {
        scaleAnimationCode = mView?.findViewById(R.id.scale_animation_code)
        scaleAnimationXml = mView?.findViewById(R.id.scale_animation_xml)
        rotateAnimationCode = mView?.findViewById(R.id.rotate_animation_code)
        rotateAnimationXml = mView?.findViewById(R.id.rotate_animation_xml)
        alphaAnimationCode = mView?.findViewById(R.id.alpha_animation_code)
        alphaAnimationXml = mView?.findViewById(R.id.alpha_animation_xml)
        tranlateAnimationCode = mView?.findViewById(R.id.translate_animation_code)
        tranlateAnimationXml = mView?.findViewById(R.id.translate_animation_xml)
        setAnimationCode = mView?.findViewById(R.id.set_animation_code)
        setAnimationCode = mView?.findViewById(R.id.set_animation_xml)
        testImg = mView?.findViewById(R.id.test_image)

        scaleAnimationCode?.requestFocus()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.scale_animation_code -> startScaleAnimationCode()
            R.id.scale_animation_xml -> startScaleAnimationXml()
            R.id.rotate_animation_code -> startRotateAnimationCode()
            R.id.rotate_animation_xml -> startRotateAnimationXml()
            R.id.alpha_animation_code -> startAlphaAnimationCode()
            R.id.alpha_animation_xml -> startAlphaAnimationXml()
            R.id.translate_animation_code -> startTranslateAnimationCode()
            R.id.translate_animation_xml -> startTranslateAnimationXml()
        }
    }

    /**
     * 缩放动画
     * 纯代码控制动画
     */
    fun startScaleAnimationCode() {
        val animation = ScaleAnimation(
            0.8f,
            1.8f,
            0.8f,
            1.8f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        animation.startOffset = 500
        animation.duration = 200
        animation.fillBefore = true
        testImg?.startAnimation(animation)
    }

    /**
     * 缩放动画
     * xml控制动画
     */
    fun startScaleAnimationXml() {
        val animation = AnimationUtils.loadAnimation(activity, R.anim.scale_test)
        testImg?.startAnimation(animation)
    }

    /**
     * 旋转动画
     * 代码控制
     */
    fun startRotateAnimationCode() {
        val rotateAnimation = RotateAnimation(
            -90f,
            90f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0f
        )
        rotateAnimation.duration = 5000
        rotateAnimation.fillAfter = true
        testImg?.startAnimation(rotateAnimation)
    }

    /**
     * 旋转动画
     * xml控制
     */
    fun startRotateAnimationXml() {
        val rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate_test)
        testImg?.startAnimation(rotateAnimation)
    }

    /**
     * 透明动画
     * code控制
     */
    fun startAlphaAnimationCode() {
        val alphaAnimation = AlphaAnimation(1f, 0f)
        alphaAnimation.fillAfter = true
        alphaAnimation.duration = 4000
        testImg?.startAnimation(alphaAnimation)
    }

    /**
     * 透明动画
     * Xml控制
     */
    fun startAlphaAnimationXml() {
        val alphaAnimation = AnimationUtils.loadAnimation(activity, R.anim.alpha_test)
        alphaAnimation.fillAfter = true
        alphaAnimation.duration = 4000
        testImg?.startAnimation(alphaAnimation)
    }

    /**
     * 平移动画
     * code控制
     */
    fun startTranslateAnimationCode() {
        val translateAnimation = TranslateAnimation(
            Animation.ABSOLUTE,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.ABSOLUTE,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        translateAnimation.fillAfter = true
        translateAnimation.duration = 4000
        testImg?.startAnimation(translateAnimation)
    }

    /**
     * 平移动画
     * Xml控制
     */
    fun startTranslateAnimationXml() {
        val translateAnimation = AnimationUtils.loadAnimation(activity, R.anim.translate_test)
        translateAnimation.fillAfter = true
        translateAnimation.duration = 4000
        testImg?.startAnimation(translateAnimation)
    }

    /**
     * 复合动画
     * code控制
     * 透明度从透明到不透明，持续2s，接着进行360度的动画，持续1s
     */
    fun startSetAnimationCode() {
        val translateAnimation = TranslateAnimation(
            Animation.ABSOLUTE,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.ABSOLUTE,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        translateAnimation.fillAfter = true
        translateAnimation.duration = 4000
        testImg?.startAnimation(translateAnimation)
    }

    /**
     * 复合动画
     * Xml控制
     * 透明度从透明到不透明，持续2s，接着进行360度的动画，持续1s
     */
    fun startSetAnimationXml() {
        val translateAnimation = AnimationUtils.loadAnimation(activity, R.anim.translate_test)
        translateAnimation.fillAfter = true
        translateAnimation.duration = 4000
        testImg?.startAnimation(translateAnimation)
    }

}