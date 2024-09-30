package com.example.proyecto_restcountry

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDAO {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): List<Country>

    @Insert
    fun insertCountry(vararg country: Country)

    @Delete
    fun deleteCountry(vararg country: Country)
}