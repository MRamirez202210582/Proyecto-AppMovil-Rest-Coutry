package com.example.proyecto_restcountry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnList= findViewById<Button>(R.id.btnList)
        val btnFavoritos = findViewById<Button>(R.id.btnFavourite)

        btnList.setOnClickListener {
            val intent = Intent(this, ListCountryActivity::class.java)
            startActivity(intent)
        }

        btnFavoritos.setOnClickListener {
            val intent = Intent(this, FavouriteCountryAcitivity::class.java)
            startActivity(intent)
        }

    }
}