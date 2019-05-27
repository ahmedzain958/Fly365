package com.zain.fly365.oneway.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zain.fly365.R
import com.zain.fly365.flightsearch.entities.Flight
import kotlinx.android.synthetic.main.item_flight_oneway.view.*

internal class OneWayFlightsAdapter(val flights: List<Flight>) :
    RecyclerView.Adapter<OneWayFlightsAdapter.OneWayFlightViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneWayFlightViewHolder {
        context = parent.context
        return OneWayFlightViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_flight_oneway,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = flights.size

    override fun onBindViewHolder(holder: OneWayFlightViewHolder, position: Int) {
        val flight: Flight = flights.get(holder.adapterPosition)
        with(holder) {
            textViewDate.text = flight.date
            textViewAirways.text = flight.airway
            textViewOriginTime.text = flight.departureTime
            textViewOriginAirport.text = flight.originAirport
            setSliderDots(flight.numberOfStops, sliderDots, context)
            textViewDuration.text = flight.duration
            textViewArrivalTime.text = flight.arrivalTime
            textViewArrivalAirport.text = flight.destinationAirport
            textViewPrice.text = flight.price
            textViewStops.text = flight.numberOfStops.toString() + " " + context.getString(R.string.stop)
            setIsRecyclable(false)
        }
    }

    fun setSliderDots(dotsCount: Int, sliderDotsPanel: LinearLayout, context: Context) {
        val dots = arrayOfNulls<ImageView>(dotsCount)
        for (i in 0 until dotsCount) {
            dots[i] = ImageView(context)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nonactive_dot))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotsPanel.addView(dots[i], params)
        }
    }

    class OneWayFlightViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewDate: TextView = view.textViewDate
        val textViewAirways: TextView = view.textViewAirways
        val textViewOriginTime: TextView = view.textViewOriginTime
        val textViewOriginAirport: TextView = view.textViewOriginAirport
        val sliderDots: LinearLayout = view.sliderDots
        val textViewDuration: TextView = view.textViewDuration
        val textViewArrivalTime: TextView = view.textViewArrivalTime
        val textViewArrivalAirport: TextView = view.textViewArrivalAirport
        val textViewPrice: TextView = view.textViewPrice
        val textViewStops: TextView = view.textViewStops
    }
}

