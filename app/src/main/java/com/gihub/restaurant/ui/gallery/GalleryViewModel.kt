package com.gihub.restaurant.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gihub.restaurant.data.AppDataManager
import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.DinePoint
import javax.inject.Inject

class GalleryViewModel
@Inject
constructor(private var appDataManager: AppDataManager) : ViewModel() {
    var loading = MutableLiveData<Boolean>()
    var bookingDone = MutableLiveData<Boolean>()
    var dinepointGallery = MutableLiveData<List<String>>()

    fun getPictures(restaurantId: Int, tableId: Int) {
        loading.value = true
        dinepointGallery.value = appDataManager.getPicsOfDinePoint(restaurantId, tableId)
        loading.value = false
    }

    fun bookTable(restaurantId: Int, tableId: Int, time: Long) {
        loading.value = true
        val selectedRestaurant = appDataManager.getRestaurant(restaurantId)
        val selectedTable = appDataManager.getTable(restaurantId, tableId)
        if (selectedRestaurant != null && selectedTable != null) {
            val booking = Bookings(
                selectedRestaurant.id,
                selectedTable.tableId,
                selectedTable.location,
                selectedRestaurant.name,
                selectedRestaurant.address,
                selectedTable.photo,
                selectedTable.seating,
                time
            )
            appDataManager.addNewBooking(booking)
            bookingDone.value = true
            loading.value = false
            return
        }
        bookingDone.value = false
    }
}