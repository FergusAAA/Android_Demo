package com.example.test.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.test.R
import com.example.test.databinding.CoutomViewActivityRootBinding

/**
 * @title:       标题
 * @package:     com.example.test.view.activity
 * @class:       CustomViewActivity
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2023/2/18 18:42
 * @author:       xuguangdong
 */
class CustomViewActivity: Activity() {
    private lateinit var binding: CoutomViewActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CoutomViewActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeViewBtn.setOnClickListener {
            binding.viewContainer.addView(
                layoutInflater.inflate(
                    R.layout.dashboard_view_root,
                    binding.viewContainer,
                    false
                )
            )
        }
    }
}