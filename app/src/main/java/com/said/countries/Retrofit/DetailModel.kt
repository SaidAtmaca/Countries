package com.said.countries.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailModel(
    @SerializedName("data")
    @Expose
    val data : CountryModel)