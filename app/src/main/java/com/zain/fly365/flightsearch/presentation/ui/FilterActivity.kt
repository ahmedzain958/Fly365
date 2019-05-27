package com.zain.fly365.flightsearch.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.zain.fly365.R
import com.zain.fly365.flightsearch.data.AirlinesEnum
import com.zain.fly365.flightsearch.data.AirportsEnum
import com.zain.fly365.flightsearch.data.StopsEnum
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.*


class FilterActivity : AppCompatActivity() {
    companion object {
        const val SELECTED_AIRWAYS_KEY = 900
        const val SELECTED_AIRPORTS_KEY = 901
        const val SELECTED_STOPS_KEY = 902
    }

    val airlines = mutableListOf<String>()
    val airports = mutableListOf<String>()
    val stops = mutableListOf<String>()
    private var airwaysSelectedList = ArrayList<String>()
    private var airPortsSelectedList = ArrayList<String>()
    private var stopsSelectedList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        for (airline in AirlinesEnum.values()) {
            airlines.add(airline.toString())
        }
        for (airline in AirportsEnum.values()) {
            airports.add(airline.toString())
        }
        for (airline in StopsEnum.values()) {
            stops.add(airline.toString())
        }
        var checkedAirlines = BooleanArray(airlines.size)
        var checkedAirPorts = BooleanArray(airports.size)
        var checkedStops = BooleanArray(stops.size)

        buttonFilterAirlines.setOnClickListener {
            val mBuilder = AlertDialog.Builder(this@FilterActivity)

            val airlinesSelectedIndexes = ArrayList<Int>()
            mBuilder.setTitle(R.string.airlines)
            mBuilder.setMultiChoiceItems(
                airlines.toTypedArray(), checkedAirlines
            ) { _, position, isChecked ->
                if (isChecked) {
                    airlinesSelectedIndexes.add(position)
                } else {
                    airlinesSelectedIndexes.remove(position)
                }
            }
            mBuilder.setCancelable(false)
            mBuilder.setPositiveButton(
                R.string.ok
            ) { _, _ ->
                airlinesSelectedIndexes.forEach {
                    airwaysSelectedList.add(airlines.get(it))
                    //todo :filter with selected airway list
                   /* val intent = Intent().putStringArrayListExtra(SELECTED_AIRWAYS_KEY, airwaysSelectedList)
                    setResult(RESULT_OK, intent)
                    finish()*/

                }
            }

            mBuilder.setNegativeButton(
                R.string.cancel
            ) { dialogInterface, i -> dialogInterface.dismiss() }

            mBuilder.setNeutralButton(
                R.string.clear_all
            ) { _, _ ->
                checkedAirlines = BooleanArray(airlines.size) { i -> false }
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }
        buttonFilterAirports.setOnClickListener {
            val mBuilder = AlertDialog.Builder(this@FilterActivity)

            val airportsSelectedIndexes = ArrayList<Int>()
            mBuilder.setTitle(R.string.airports)
            mBuilder.setMultiChoiceItems(
                airports.toTypedArray(), checkedAirPorts
            ) { _, position, isChecked ->
                if (isChecked) {
                    airportsSelectedIndexes.add(position)
                } else {
                    airportsSelectedIndexes.remove(position)
                }
            }
            mBuilder.setCancelable(false)
            mBuilder.setPositiveButton(
                R.string.ok
            ) { _, _ ->
                airportsSelectedIndexes.forEach {
                    airPortsSelectedList.add(airports.get(it))
                    //todo :filter with selected airports list
                   /* Intent().putExtra(SELECTED_AIRPORTS_KEY, airPortsSelectedList).also {
                        setResult(RESULT_OK, it)
                        finish()
                    }*/
                }
            }

            mBuilder.setNegativeButton(
                R.string.cancel
            ) { dialogInterface, i -> dialogInterface.dismiss() }

            mBuilder.setNeutralButton(
                R.string.clear_all
            ) { _, _ ->
                checkedAirPorts = BooleanArray(airports.size) { i -> false }
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }
        buttonFilterStops.setOnClickListener {
            val mBuilder = AlertDialog.Builder(this@FilterActivity)

            val stopsSelectedIndexes = ArrayList<Int>()
            mBuilder.setTitle(R.string.stops)
            mBuilder.setMultiChoiceItems(
                stops.toTypedArray(), checkedStops
            ) { _, position, isChecked ->
                if (isChecked) {
                    stopsSelectedIndexes.add(position)
                } else {
                    stopsSelectedIndexes.remove(position)
                }
            }
            mBuilder.setCancelable(false)
            mBuilder.setPositiveButton(
                R.string.ok
            ) { _, _ ->
                stopsSelectedIndexes.forEach {
                    stopsSelectedList.add(stops.get(it))
                    //todo :filter with selected stops list
                   /* Intent().putExtra(SELECTED_STOPS_KEY, stopsSelectedList).also {
                        setResult(RESULT_OK, it)
                        finish()
                    }*/
                }
            }

            mBuilder.setNegativeButton(
                R.string.cancel
            ) { dialogInterface, i -> dialogInterface.dismiss() }

            mBuilder.setNeutralButton(
                R.string.clear_all
            ) { _, _ ->
                checkedStops = BooleanArray(stops.size) { i -> false }
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }

}

fun main() {
    var checkedAirlines = BooleanArray(5)
    checkedAirlines.forEach { }
    checkedAirlines.forEach {
        print(it)
    }
}
