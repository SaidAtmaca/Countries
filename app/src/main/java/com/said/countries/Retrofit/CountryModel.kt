package com.said.countries.Retrofit

import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("code")
    @Expose
    val code : String ,

    @SerializedName("name")
    @Expose
    val name:String ,

    @SerializedName("wikiDataId")
    @Expose
    val wikiDatId: String,

    @SerializedName("flagImageUri")
    @Expose
    val flagImageUri: String
)
