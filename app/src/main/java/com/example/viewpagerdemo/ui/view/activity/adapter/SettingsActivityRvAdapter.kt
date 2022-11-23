package com.example.viewpagerdemo.ui.view.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerdemo.R
import com.example.viewpagerdemo.logic.model.City

class SettingsActivityRvAdapter(val storedList: List<City>) :
    RecyclerView.Adapter<SettingsActivityRvAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityNameTV: TextView = view.findViewById(R.id.item_city_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.rv_city_item, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityNameTV.text = storedList[position].name
    }

    override fun getItemCount(): Int = storedList.size
}