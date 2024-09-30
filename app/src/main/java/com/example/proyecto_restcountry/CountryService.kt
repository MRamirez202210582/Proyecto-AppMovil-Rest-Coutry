package com.example.proyecto_restcountry


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {
    @GET("v3.1/all")
    fun getAllCountries(@Query("name") name: String, @Query("flags") flags: String): Call<CountryResponse>
}