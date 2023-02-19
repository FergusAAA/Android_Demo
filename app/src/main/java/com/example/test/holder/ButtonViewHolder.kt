package com.example.test.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

/**
 * @title:       标题
 * @project:     Baymax
 * @package:     com.example.test.holder
 * @class:       ButtonViewHolder
 * @description:
 * <p>
 *
 * </p>
 *
 * @version:   1.0
 * @createDate:   2022/5/14 13:28
 * @author:       xuguangdong
 */
class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var btnName = itemView.findViewById<TextView>(R.id.btn_view)
}