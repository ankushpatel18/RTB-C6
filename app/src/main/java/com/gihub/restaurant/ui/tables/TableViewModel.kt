package com.gihub.restaurant.ui.tables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gihub.restaurant.data.AppDataManager
import com.gihub.restaurant.data.model.DinePoint
import javax.inject.Inject

class TableViewModel
@Inject
constructor(private var appDataManager: AppDataManager) : ViewModel() {
    var loading = MutableLiveData<Boolean>()
    var tables = MutableLiveData<List<DinePoint>>()

    fun getTables(restaurantId: Int) {
        loading.value = true
        tables.value = appDataManager.getTablesOfRestaurant(restaurantId)
        loading.value = false
    }

}