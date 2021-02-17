package com.gihub.restaurant.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("neighborhood") val neighborhood: String,
    @SerializedName("photograph") val photo: String,
    @SerializedName("address") val address: String,
    @SerializedName("cuisine_type") val cuisineType: String,
    @SerializedName("dinepoints") val tables: List<DinePoint>
) : Parcelable