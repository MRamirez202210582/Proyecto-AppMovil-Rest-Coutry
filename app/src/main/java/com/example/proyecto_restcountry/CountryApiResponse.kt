package com.example.proyecto_restcountry

class CountryApiResponse(
    private var name: CountryNameApiResponse,
    private var capital: String,
    private var region: String,
    private var population: Int,
    private var area: Double,
    private var flags: CountryFlagsApiResponse
) {
    fun toCountry(): Country {
        return Country(
            name = name.common,
            capital = capital,
            region = region,
            population = population,
            area = area,
            flagUrl = flags.svg
        )
    }
}

data class CountryNameApiResponse(
    var common: String,
    var official: String
)

data class CountryFlagsApiResponse(
    var svg: String,
    var png: String
)