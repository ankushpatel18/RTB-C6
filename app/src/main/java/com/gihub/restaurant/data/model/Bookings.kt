package com.gihub.restaurant.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bookings(
    @SerializedName("restaurant_id") val id: Int,
    @SerializedName("table_id") val tableId: Int,
    @SerializedName("location") val location: String?,
    @SerializedName("restaurant_name") val restaurantName: String,
    @SerializedName("address") val restaurantAddress: String,
    @SerializedName("photo") val photo: String?,
    @SerializedName("seating") val seating: Int,
    @SerializedName("time") val time: Long
) : Parcelable