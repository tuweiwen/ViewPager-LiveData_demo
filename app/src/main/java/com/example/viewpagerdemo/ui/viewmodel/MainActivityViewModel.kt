package com.example.viewpagerdemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewpagerdemo.logic.model.City

class MainActivityViewModel : ViewModel() {
    val cityList = MutableLiveData<ArrayList<City>>()
}