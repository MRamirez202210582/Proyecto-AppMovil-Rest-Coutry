package com.example.proyecto_restcountry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "capital")
    val capital: String?,

    @ColumnInfo(name = "region")
    val region: String?,

    @ColumnInfo(name = "population")
    val population: Int?,

    @ColumnInfo(name = "area")
    val area: Double?,

    @ColumnInfo(name = "flagUrl")
    val flagUrl: String?,
)