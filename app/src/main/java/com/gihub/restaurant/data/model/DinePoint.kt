package com.gihub.restaurant.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DinePoint(
    @SerializedName("table_id") val tableId: Int,
    @SerializedName("location") val location: String?,
    @SerializedName("comment") val comment: String?,
    @SerializedName("photo") val photo: String?,
    @SerializedName("seating") val seating: Int,
    @SerializedName("gallery") val gallery: List<String>,
    @SerializedName("selected") var isSelected: Boolean = false
) : Parcelable