package com.gihub.restaurant.ui.restaurants

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gihub.restaurant.data.AppDataManager
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.data.model.Restaurant
import javax.inject.Inject

class MainViewModel
    @Inject
    constructor(var appDataManager: AppDataManager): ViewModel() {

    var loading = MutableLiveData<Boolean>()
    var restaurants = MutableLiveData<List<Restaurant>>()

    fun getRestaurants() {
        loading.value = true
        restaurants.value = appDataManager.getAllRestaurants()
        loading.value = false
    }

}