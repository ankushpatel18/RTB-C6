package com.gihub.restaurant.ui.bookings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gihub.restaurant.data.AppDataManager
import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.data.model.Restaurant
import javax.inject.Inject

class BookingViewModel
    @Inject
    constructor(var appDataManager: AppDataManager): ViewModel() {

    var loading = MutableLiveData<Boolean>()
    var recentBookings = MutableLiveData<List<Bookings>>()

    fun getBookings() {
        loading.value = true
        recentBookings.value = appDataManager.getAllBookings()
        loading.value = false
    }

}