package com.example.test.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.holder.ButtonViewHolder

/**
 * @title:       标题
 * @project:     AndroidDemo
 * @package:     com.example.test.adapter
 * @class:       ButtonAdapter
 * @description:
 * <p>
 * 类描述
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/3/5 17:45
 * @author:       NC0955
 */
class ButtonAdapter(mDataList: List<String>? = null, private val mContext: Context) :
    RecyclerView.Adapter<ButtonViewHolder>() {

    var onClickListener: OnClickListener? = null

    var mDataList: List<String>? = mDataList
        set(value) {
            field = value
            Log.w(TAG, "setDataList: $value")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ButtonViewHolder(LayoutInflater.from(mContext).inflate(R.layout.button_item, parent, false))

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.btnName.text = mDataList?.get(position) ?: ""
        holder.itemView.setOnClickListener { onClickListener?.onClick(position) }
    }

    override fun getItemCount(): Int = mDataList?.size ?: 0

    fun interface OnClickListener {
        fun onClick(position: Int)
    }


    companion object {
        const val TAG = "ButtonAdapter"
    }
}