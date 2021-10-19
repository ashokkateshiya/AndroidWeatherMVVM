package com.ashok.weatherapp.model

import com.google.gson.annotations.SerializedName

data class HighlightResult(

    @SerializedName("country")
    val country: Country? = null,

    @SerializedName("name")
    val name: Name? = null
)
