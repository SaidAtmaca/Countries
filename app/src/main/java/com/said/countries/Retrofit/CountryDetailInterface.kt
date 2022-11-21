package com.said.countries.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CountryDetailInterface {
    @Headers(
        "X-RapidAPI-Host: wft-geo-db.p.rapidapi.com",
        "X-RapidAPI-Key: 9134062c4dmsh6c22c6c26a6664ep100c57jsnf7c5dc6bb46a")
    @GET("countries/"+"{code}")
    fun getCountryDetails(@Path("code") code: String?): Call<DetailModel>
}