package com.gihub.restaurant.data.remote

import android.content.Context
import android.content.SharedPreferences
import com.gihub.restaurant.data.local.PreferenceHelper
import com.gihub.restaurant.data.model.Bookings
import com.gihub.restaurant.data.model.DinePoint
import com.gihub.restaurant.data.model.Restaurant
import com.gihub.restaurant.utils.Constants.PrefConst.BOOKING_LIST
import com.gihub.restaurant.utils.Constants.PrefConst.PREFERENCE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import javax.inject.Inject


class RemoteDataManager
@Inject constructor(context: Context) : IRemoteSource {
    var restaurantJson: List<Restaurant>
    lateinit var preferenceManager: SharedPreferences

    init {
        restaurantJson = readDataFromJsonFile(context)
        preferenceManager = PreferenceHelper(context).customPrefs(PREFERENCE_NAME)
    }

    private fun readDataFromJsonFile(context: Context): List<Restaurant> {
        var restaurantList: ArrayList<Restaurant> = arrayListOf()
        val json = try {
            val inputStream: InputStream = context.assets.open("restaurants.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
        if (json.isNullOrEmpty().not()) {
            val listType: Type = object : TypeToken<ArrayList<Restaurant>>() {}.type
            restaurantList = Gson().fromJson(json, listType)
        }
        return restaurantList
    }

    override fun getAllRestaurants(): List<Restaurant> {
        return restaurantJson
    }

    override fun getRestaurant(restaurantId: Int): Restaurant? {
        for (item in restaurantJson) {
            if (item.id == restaurantId) {
                return item
            }
        }
        return null
    }

    override fun getTable(restaurantId: Int, tableId: Int): DinePoint? {
        for (item in restaurantJson) {
            if (item.id == restaurantId) {
                for(table in item.tables) {
                    return table
                }
            }
        }
        return null
    }

    override fun getTablesOfRestaurant(restaurantId: Int): List<DinePoint> {
        for (item in restaurantJson) {
            if (item.id == restaurantId) {
                return item.tables
            }
        }
        return emptyList()
    }


    override fun getPicsOfDinePoint(restaurantId: Int, tableId: Int): List<String> {
        for (item in restaurantJson) {
            if (item.id == restaurantId) {
                for(table in item.tables) {
                    return table.gallery
                }
            }
        }
        return emptyList()
    }

    override fun addNewBooking(bookings: Bookings) {
        var existingBookings : MutableList<Bookings>? = getAllBookings()
        if (existingBookings == null) {
            existingBookings = arrayListOf()
        }
            existingBookings.add(bookings)
        preferenceManager.edit().putString(BOOKING_LIST, Gson().toJson(existingBookings)).apply()
    }

    override fun getAllBookings(): ArrayList<Bookings>? {
        if (preferenceManager.contains(BOOKING_LIST)) {
            val bookingString = preferenceManager.getString(BOOKING_LIST, null)
            if (bookingString != null) {
                val listType: Type = object : TypeToken<ArrayList<Bookings>>() {}.type
                val existingBookings: ArrayList<Bookings> = Gson().fromJson(bookingString, listType)
                return existingBookings
            }
        }
        return null
    }


}