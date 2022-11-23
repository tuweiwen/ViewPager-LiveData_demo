package com.example.viewpagerdemo.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpagerdemo.databinding.ActivitySettingsBinding
import com.example.viewpagerdemo.logic.dao.cityListDao
import com.example.viewpagerdemo.logic.model.City
import com.example.viewpagerdemo.ui.view.activity.adapter.SettingsActivityRvAdapter
import com.example.viewpagerdemo.ui.viewmodel.SettingsViewModel

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val cityListViewModel by lazy { ViewModelProviders.of(this).get(SettingsViewModel::class.java)}

    // BUG: 删除一次数据后再添加数据会添加为上一次输入的数据，而非本次输入希望添加的数据。但是在xml文件中是正常的
    // BUG-FIXED: 使用yourRecyclerView.adapter.notifyItemChanged(position, count)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get view component
        val settingsRv = binding.settingsRecyclerView
        val addCityBtn = binding.addCityBtn
        val removeCityBtn = binding.removeCityBtn
        val placeInput = binding.placeInput

        // liveData init
        if (cityListDao.isCitySaved()) {
            cityListViewModel.cityList.value = cityListDao.getCityList()
        } else {
            cityListViewModel.cityList.value = ArrayList()
        }

        // attach adapter&layoutManager to recyclerView
        settingsRv.apply {
            this.layoutManager =
                LinearLayoutManager(this@SettingsActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = SettingsActivityRvAdapter(cityListViewModel.cityList.value!!)
        }

        // add city
        addCityBtn.setOnClickListener {
            val cityName = placeInput.text.toString().trim()
            if (cityName != "") {
                Log.e("", "", )
                // save in ViewModel(LiveData)
                cityListViewModel.cityList.value!!.add(City(cityName))
                // save in sharedPreference
                cityListDao.saveCity(City(cityName))
                // refresh Rv
                settingsRv.adapter!!.notifyItemInserted(cityListViewModel.cityList.value!!.size)
                //this step is important!!!
                settingsRv.adapter!!.notifyItemRangeChanged(cityListViewModel.cityList.value!!.size - 1, 1)
                placeInput.setText("")
            } else {
                Toast.makeText(this, "please enter not-null string", Toast.LENGTH_SHORT).show()
            }
        }

        // remove city
        removeCityBtn.setOnClickListener {
            if (cityListViewModel.cityList.value!!.size > 0) {
                // remove in ViewModel(LiveData)
                cityListViewModel.cityList.value!!.removeAt(cityListViewModel.cityList.value!!.size - 1)
                // remove in sharedPreference
                cityListDao.removeCity(cityListDao.getCityCount() - 1)
                // refresh
                settingsRv.adapter?.notifyItemRemoved(cityListViewModel.cityList.value!!.size + 1)
                // this step is important!!!
                settingsRv.adapter!!.notifyItemRangeChanged(cityListViewModel.cityList.value!!.size - 1, 1)
            } else {
                Toast.makeText(this, "list is empty", Toast.LENGTH_SHORT).show()
            }
5        }
    }
}