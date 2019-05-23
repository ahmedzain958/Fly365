package com.zain.fly365.flightsearch.presentation.ui.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

import com.zain.fly365.R
import com.zain.fly365.flightsearch.presentation.ui.activity.AirportsActivity
import kotlinx.android.synthetic.main.fragment_one_way.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val AIRPORTS_REQUEST_CODE = 600
private const val ARG_PARAM2 = "param2"


class OneWayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one_way, container, false)
        val cityFromCard = view.findViewById<CardView>(R.id.cityFromCard)
        cityFromCard.setOnClickListener {
            val intent = Intent(context, AirportsActivity::class.java)
            startActivityForResult(intent, AIRPORTS_REQUEST_CODE)
        }
        return view
    }


}
