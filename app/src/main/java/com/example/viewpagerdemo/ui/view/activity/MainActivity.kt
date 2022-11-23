package com.example.viewpagerdemo.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagerdemo.ui.view.fragment.adapter.CustomRvFragmentAdapter
import com.example.viewpagerdemo.R
import com.example.viewpagerdemo.logic.dao.cityListDao
import com.example.viewpagerdemo.ui.viewmodel.MainActivityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
//    lateinit var adapter: CustomRvAdapter
    lateinit var adapter: CustomRvFragmentAdapter
    private val cityListViewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (cityListDao.isCitySaved()) {
            cityListViewModel.cityList.value = cityListDao.getCityList()
        } else {
            cityListViewModel.cityList.value = ArrayList()
        }

//        adapter = CustomRvAdapter()
        adapter = CustomRvFragmentAdapter(this)
        for(i in 0 until cityListViewModel.cityList.value!!.count())
            adapter.addFragment(cityListViewModel.cityList.value!![i])

        viewPager2 = findViewById(R.id.view_pager)
        val tablayout: TabLayout = findViewById(R.id.tabLayout)

        viewPager2.adapter = adapter
        TabLayoutMediator(tablayout, viewPager2) { tab, position ->
//            tab.text = position.toString()
            tab.text = cityListViewModel.cityList.value!![position].name
        }.attach()

//        viewPager2.registerOnPageChangeCallback (object: ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                Toast.makeText(this@MainActivity, "page changed!", Toast.LENGTH_SHORT).show()
//            }
//        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return true;
    }
}