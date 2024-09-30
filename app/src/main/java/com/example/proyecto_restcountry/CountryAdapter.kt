package com.example.proyecto_restcountry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CountryAdapter(private val countries: List<Country>, private val clickListener: OnItemClickListener) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countries = countries[position]
        holder.bind(countries, clickListener)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryName = itemView.findViewById<TextView>(R.id.tvName)
        private val capital = itemView.findViewById<TextView>(R.id.tvCapital)
        private val region = itemView.findViewById<TextView>(R.id.tvRegion)
        private val population = itemView.findViewById<TextView>(R.id.tvPopulation)
        private val area = itemView.findViewById<TextView>(R.id.tvArea)
        private val flag=itemView.findViewById<ImageView>(R.id.ivFlag)
        private val addToFavorites = itemView.findViewById<ImageButton>(R.id.ibFavorite)

        fun bind(countries: Country, listener: OnItemClickListener) {
            countryName.text = countries.name
            capital.text = countries.capital
            region.text = countries.region
            Picasso.get().load(countries.flagUrl).into(flag)
            population.text = countries.population.toString()
            area.text = countries.area.toString()

            addToFavorites.setOnClickListener {
                listener.OnItemClicked(countries)
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClicked( countries:Country)
    }
}