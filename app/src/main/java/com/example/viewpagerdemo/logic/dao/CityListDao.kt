package com.example.viewpagerdemo.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.viewpagerdemo.ViewPagerDemoApplication
import com.example.viewpagerdemo.logic.model.City

// todo( need to run in coroutine )
object CityListDao {
    // create sharedPreferences instance
    private fun sharedPreferences() =
        ViewPagerDemoApplication.context.getSharedPreferences("city_list", Context.MODE_PRIVATE)

    // city$position
    fun saveCity(city: City) {
        val oldCount = sharedPreferences().getInt("count", 0)
        sharedPreferences().edit {
            putString("city" + (oldCount + 1), city.name)
            putInt("count", oldCount + 1)
            commit()
        }
    }

    fun getCity(index: Int): City? {
        val city = sharedPreferences().getString("city" + (index + 1), null)
        return if (city != null) {
            City(city)
        } else {
            null
        }
    }

    fun getCityList(): ArrayList<City> {
        val returnCityList = ArrayList<City>()
        for (i in 0 until getCityCount()) {
            returnCityList.add(getCity(i)!!)
        }
        return returnCityList
    }

    fun removeCity(index: Int) {
        val oldCount = getCityCount()
        if (oldCount == 0 || index < 0 || index > oldCount - 1)
            return
        sharedPreferences().edit {
            putInt("count", oldCount - 1)
            for (i in index until oldCount - 1) {
                putString("city$i", getCity(i + 1)!!.name)
            }
            remove("city$oldCount")
            commit()
        }
    }

    fun isCitySaved(): Boolean {
        if (sharedPreferences().getInt("count", 0) == 0) {
            return false
        }
        return true
    }

    // return -1 if doesn't have this property
    fun getCityCount(): Int = sharedPreferences().getInt("count", -1)
}