package com.example.viewpagerdemo.ui.view.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerdemo.R

class CustomRvAdapter: RecyclerView.Adapter<CustomRvAdapter.ViewHolder>() {
    private var fragmentList = ArrayList<Int>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.item_fragment_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = position.toString()
    }

    override fun getItemCount(): Int = fragmentList.size

    fun addFragment() {
        fragmentList.add(itemCount + 1)
        notifyItemInserted(itemCount)
    }

    fun removeFragment() {
        fragmentList.remove(itemCount)
        notifyItemRemoved(itemCount - 1)
    }
}