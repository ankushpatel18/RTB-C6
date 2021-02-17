package com.gihub.restaurant.data.remote

import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.data.model.Restaurant

interface IRemoteSource {
    fun getAllRestaurants()
            : List<Restaurant>

    fun getRestaurant(restaurantId: Int): Restaurant?

    fun getTable(restaurantId: Int, tableId: Int): DinePoint?

    fun getTablesOfRestaurant(restaurantId: Int)
            : List<DinePoint>

    fun getPicsOfDinePoint(restaurantId: Int, tableId: Int): List<String>

    fun addNewBooking(bookings: Bookings)

    fun getAllBookings(): List<Bookings>?
}