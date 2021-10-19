package com.ashok.weatherapp.model

import android.os.Parcelable
import com.ashok.weatherapp.model.ListItem
import com.google.gson.annotations.SerializedName
import com.ashok.weatherapp.model.City
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastResponse(

    @SerializedName("city")
    val city: City?,

    @SerializedName("cnt")
    val cnt: Int?,

    @SerializedName("cod")
    val cod: String?,

    @SerializedName("message")
    val message: Double?,

    @SerializedName("list")
    val list: List<ListItem>?
) : Parcelable
