package com.example.proyecto_restcountry

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FavouriteCountryAcitivity: AppCompatActivity(), CountryAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favourite_country)

        setSupportActionBar(findViewById(R.id.toolbar3))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        recyclerView = findViewById(R.id.rvFavoruite)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadFavoritePersonas()
    }

    private fun loadFavoritePersonas() {
        val dao = AppDatabase.getDatabase(this).countryDAO()
        val favoritePersonas = dao.getAllCountries()

        if (favoritePersonas.isNotEmpty()) {
            adapter = CountryAdapter(favoritePersonas.toMutableList(), this)
            recyclerView.adapter = adapter
        } else {
            Toast.makeText(this, "No hay Paises favoritos.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun OnItemClicked(countries: Country) {
        val dao = AppDatabase.getDatabase(this).countryDAO()
        dao.deleteCountry(countries)

        Toast.makeText(this, "Pais ${countries.name} eliminado de favoritos", Toast.LENGTH_SHORT).show()
    }
}