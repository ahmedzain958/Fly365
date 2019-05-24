package com.zain.fly365.flightsearch.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zain.fly365.R
import com.zain.fly365.flightsearch.data.Airport
import kotlinx.android.synthetic.main.item_airport.view.*

internal class AirportsAdapter(val airportsList: List<Airport>, val airportClickedListener: OnAirportClickedListener) :
    RecyclerView.Adapter<AirportsAdapter.AirportViewHolder>() {

    interface OnAirportClickedListener {
        fun setOnAirportClicked(airport: Airport)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        return AirportViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_airport, parent, false))
    }

    override fun getItemCount(): Int = airportsList.size

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        val airport: Airport = airportsList.get(holder.adapterPosition)
        with(holder) {
            textViewAirport.text = airport.name
            textViewAirport.setOnClickListener {
                airportClickedListener.setOnAirportClicked(airport)
            }
        }
    }

    class AirportViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewAirport: TextView = view.textViewAirport
    }
}

