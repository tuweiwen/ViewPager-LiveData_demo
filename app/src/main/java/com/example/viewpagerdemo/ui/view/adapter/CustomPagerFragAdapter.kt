package com.example.viewpagerdemo.ui.view.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.viewpagerdemo.logic.model.City
import com.example.viewpagerdemo.ui.view.fragment.CustomPagerFragment

class CustomPagerFragAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private var fragmentList = ArrayList<City>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment =
        CustomPagerFragment(fragmentList[position])

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
//        Log.e("CustomRvFragmentAdapter", "onBindViewHolder: $holder")
//        val textView: TextView = holder.itemView.findViewById(R.id.item_fragment_tv)
//        textView.text = position.toString()
    }

    fun addFragment(city: City) {
        fragmentList.add(city)
        notifyItemInserted(itemCount)
    }

    fun removeFragment(city: City) {
        fragmentList.remove(city)
        notifyItemRemoved(itemCount - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearFragment() {
        fragmentList.clear()
        notifyDataSetChanged()
    }
}