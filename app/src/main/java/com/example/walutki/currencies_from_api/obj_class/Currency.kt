package com.example.walutki.currencies_from_api.obj_class


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Rates
)