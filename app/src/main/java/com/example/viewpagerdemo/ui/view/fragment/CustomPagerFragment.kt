package com.example.viewpagerdemo.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viewpagerdemo.R
import com.example.viewpagerdemo.logic.model.City

class CustomPagerFragment(val city: City) : Fragment() {
//    companion object {
//        fun newInstance(): CustomFragment = CustomFragment(null).apply {
//            arguments = bundleOf()
//        }
//
//        inline fun <reified T : Fragment> newFragmentInstance(vararg params: Pair<String, Any>) =
//            T::class.java.newInstance().apply {
//                arguments = bundleOf(*params)
//            }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_fragment, container, false)
        val textView: TextView = view.findViewById(R.id.item_fragment_tv)
//        textView.text = arguments?.getInt("position", 0).toString()
        textView.text = city.name
        return view
    }

}