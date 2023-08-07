package com.example.test.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
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
class ButtonAdapter(mDataList: List<String>, private val mContext: Context) :
    RecyclerView.Adapter<ButtonViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            val b = oldItem == newItem
            Log.d(TAG, "areItemsTheSame: $b")
            return b
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            val b = oldItem == newItem
            Log.d(TAG, "areContentsTheSame: $b")
            return b
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var onClickListener: OnClickListener? = null

    init {
        differ.submitList(mDataList)
    }

    /**
     * 当前数据item
     */
    private fun getItem(position: Int) = differ.currentList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ButtonViewHolder(LayoutInflater.from(mContext).inflate(R.layout.button_item, parent, false))

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: ")
        holder.btnName.text = getItem(position)
        holder.itemView.setOnClickListener { onClickListener?.onClick(position) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    /**
     * 更新数据
     */
    fun submit(list: List<String>) {
        differ.submitList(list)
    }

    fun interface OnClickListener {
        fun onClick(position: Int)
    }


    companion object {
        const val TAG = "ButtonAdapter"
    }
}