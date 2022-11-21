package com.said.countries.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SavedModel(
    @SerializedName("data")
    @Expose
    val data : List<SavedCountryModel>)