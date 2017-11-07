package com.example.chengguo.rllayout

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 *
 * @author Chengguo on 2017/11/7.
 */
class LoadMoreWrapperAdapter(private val dataList: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_recyclerview, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is RecyclerViewHolder) {
            holder.tvItem.text = dataList[position]
        }
    }

    class RecyclerViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        internal var tvItem: TextView = itemView.findViewById<View>(R.id.tv_item) as TextView
    }

}