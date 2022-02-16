package com.example.test.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.test.R
import com.example.test.fragment.BaseFragment
import com.example.test.fragment.DrawableAnimationFragment
import com.example.test.fragment.ViewAnimationFragment

class TweenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnView: TextView
    private lateinit var btnDrawable: TextView
    private lateinit var animationContainer: FrameLayout
    private lateinit var currentFragment: BaseFragment
    private val fragmentManager = getSupportFragmentManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween)
        initView()
    }

    private fun initView() {
        btnView = findViewById(R.id.btn_view)
        btnDrawable = findViewById(R.id.btn_drawable)
        animationContainer = findViewById(R.id.animation_container)

        btnView.requestFocus()

        btnView.setOnClickListener(this)
        btnDrawable.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_view -> showFragment(true)
            R.id.btn_drawable -> showFragment(false)
        }
    }

    /**
     * @method: 展示fragment
     * @description:
     * <p>
     *
     * </p>
     *
     * @param null
     * @return
     * @date: 2022/1/4 17:15
     * @author: xuguangdong
     */
    private fun showFragment(isViewAnimation: Boolean) {
        val transaction:FragmentTransaction = fragmentManager.beginTransaction()
        if (isViewAnimation) {
            currentFragment = ViewAnimationFragment()
        } else {
            currentFragment = DrawableAnimationFragment()
        }
        transaction.add(R.id.animation_container, currentFragment!!)
        transaction.commit()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (currentFragment.dispatchKeyEvent(event) == true) {
            return true
        }
        if (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentFragment.isVisible == true) {
                fragmentManager.beginTransaction().remove(currentFragment!!).commit()
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }
}