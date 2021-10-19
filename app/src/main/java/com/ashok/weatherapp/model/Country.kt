package com.ashok.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("matchLevel")
    val matchLevel: String? = null,

    @SerializedName("value")
    val value: String? = null,

    @SerializedName("matchedWords")
    val matchedWords: List<Any?>? = null
)
