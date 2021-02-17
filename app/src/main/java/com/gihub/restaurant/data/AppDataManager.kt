package com.gihub.restaurant.data

import android.content.Context
import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.data.model.Restaurant
import com.gihub.restaurant.data.remote.RemoteDataManager
import javax.inject.Inject

class AppDataManager
@Inject constructor(
    private var context: Context,
    private var remoteDataManager: RemoteDataManager
) : IAppDataSource {
    override fun getAllRestaurants(): List<Restaurant> = remoteDataManager.getAllRestaurants()

    override fun getRestaurant(restaurantId: Int): Restaurant? = remoteDataManager.getRestaurant(restaurantId)

    override fun getTable(restaurantId: Int, tableId: Int): DinePoint? = remoteDataManager.getTable(restaurantId, tableId)

    override fun getTablesOfRestaurant(restaurantId: Int): List<DinePoint> = remoteDataManager.getTablesOfRestaurant(restaurantId)

    override fun getPicsOfDinePoint(restaurantId: Int, tableId: Int): List<String> = remoteDataManager.getPicsOfDinePoint(restaurantId, tableId)

    override fun addNewBooking(bookings: Bookings) {
        remoteDataManager.addNewBooking(bookings)
    }

    override fun getAllBookings(): List<Bookings>? =  remoteDataManager.getAllBookings()

}