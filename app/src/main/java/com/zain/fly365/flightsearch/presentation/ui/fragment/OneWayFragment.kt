package com.zain.fly365.flightsearch.presentation.ui.fragment


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView

import com.zain.fly365.R
import com.zain.fly365.flightsearch.data.Airport
import com.zain.fly365.flightsearch.presentation.ui.activity.AirportsActivity


class OneWayFragment : Fragment() {
    companion object {
        private const val AIRPORTS_REQUEST_CODE = 600
    }

    private lateinit var textViewCityFrom: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_way, container, false)
        view?.let {
            initUI(view)
        }
        return view
    }

    private fun initUI(view: View) {
        val cityFromCard = view.findViewById<CardView>(R.id.cityFromCard)
        textViewCityFrom = view.findViewById<TextView>(R.id.textViewCityFrom)
        cityFromCard.setOnClickListener {
            Intent(context, AirportsActivity::class.java).also { intent ->
                startActivityForResult(intent, AIRPORTS_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == AIRPORTS_REQUEST_CODE) {
                data?.let {
                    val airport = data.getSerializableExtra(AirportsActivity.SELECTED_AIRPORT_KEY) as Airport
                    textViewCityFrom.setText(airport.name)
                }
            }
        }
    }

}
