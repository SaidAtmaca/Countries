package com.said.countries.Retrofit

import android.widget.ImageView
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("data")
    @Expose
    val data : List<CountryModel>)
