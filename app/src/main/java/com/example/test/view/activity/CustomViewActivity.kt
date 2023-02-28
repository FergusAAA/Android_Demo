package com.example.test.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.databinding.CoutomViewActivityRootBinding
import com.example.test.model.viewmodel.CustomViewModel

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
class CustomViewActivity : FragmentActivity() {
    private lateinit var binding: CoutomViewActivityRootBinding
    private lateinit var viewModel: CustomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CoutomViewActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider.NewInstanceFactory().create(CustomViewModel::class.java)

        binding.changeViewBtn.setOnClickListener {
            viewModel.countPlus()
        }

        viewModel.countLiveData.observe(this, Observer {
            when (it) {
                1 -> layoutInflater.inflate(
                    R.layout.dashboard_view_root,
                    binding.viewContainer,
                    true
                )
                2 -> {
                    binding.viewContainer.removeAllViews()
                    layoutInflater.inflate(
                        R.layout.camera_view_root,
                        binding.viewContainer,
                        true
                    )
                }
                3-> {
                    binding.viewContainer.removeAllViews()
                    viewModel.clear()
                }
            }
        })
    }
}