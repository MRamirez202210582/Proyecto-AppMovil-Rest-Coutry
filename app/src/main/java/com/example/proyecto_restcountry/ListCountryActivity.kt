package com.example.proyecto_restcountry

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class ListCountryActivity : AppCompatActivity(), CountryAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_country)


        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.rvList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchCountries()
    }

    private fun fetchCountries() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val countryService = retrofit.create(CountryService::class.java)
        val request = countryService.getAllCountries("name","flags")

        request.enqueue(object : Callback<CountryResponse> {
            override fun onResponse(
                call: Call<CountryResponse>,
                response: Response<CountryResponse>
            ) {
                if (response.isSuccessful) {
                    val countryApiResponses = response.body()?.name ?: emptyList()
                    val countries = countryApiResponses.map { it.toCountry() }

                    if (countries.isEmpty()) {
                        Toast.makeText(this@ListCountryActivity, "No se encontraron países", Toast.LENGTH_SHORT).show()
                    } else {
                        adapter = CountryAdapter(countries, this@ListCountryActivity)
                        recyclerView.adapter = adapter
                    }
                } else {
                    Toast.makeText(
                        this@ListCountryActivity,
                        "Error en la respuesta: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CountryResponse>, t: Throwable) {
                Toast.makeText(
                    this@ListCountryActivity,
                    "Error de red: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun OnItemClicked(country: Country) {
        val dao = AppDatabase.getDatabase(this).countryDAO()
        dao.insertCountry(country)
        Toast.makeText(this, "${country.name} agregado a favoritos", Toast.LENGTH_SHORT).show()
    }
}