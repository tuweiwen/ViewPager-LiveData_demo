package com.example.viewpagerdemo.ui.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagerdemo.R
import com.example.viewpagerdemo.logic.dao.CityListDao
import com.example.viewpagerdemo.ui.view.adapter.CustomPagerFragAdapter
import com.example.viewpagerdemo.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val cityListViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate: invoked!")
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume: invoked!")

        // get view component
        val viewPager2: ViewPager2 = findViewById(R.id.view_pager)
//        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        // get adapter instance & set ViewPager's adapter
        val adapter = CustomPagerFragAdapter(this)
        viewPager2.adapter = adapter


        GlobalScope.launch {
            // whether city data is saved
            if (CityListDao.isCitySaved()) {
                cityListViewModel.cityList.postValue(CityListDao.getCityList())
            } else {
                cityListViewModel.cityList.postValue(ArrayList())
            }
        }

        cityListViewModel.cityList.observe(this) { cityList ->
            adapter.clearFragment()
            adapter.notifyDataSetChanged()
            // add fragment page
            for (i in 0 until cityList.count())
                adapter.addFragment(cityList[i])

            viewPager2.adapter = adapter
            // todo ( BUG: bind tableLayout with the viewpager
            //        the tab's count(position) should be updated )
//            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
//                tab.text = cityList[position].name
//            }.attach()
        }

        // page changed callback function here
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
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
        return true
    }
}