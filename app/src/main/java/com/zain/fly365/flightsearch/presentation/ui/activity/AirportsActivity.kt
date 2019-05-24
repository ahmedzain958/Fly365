package com.zain.fly365.flightsearch.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.zain.fly365.R
import com.zain.fly365.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_airports.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.zain.fly365.flightsearch.data.Airport
import com.zain.fly365.flightsearch.presentation.ui.adapter.AirportsAdapter
import io.reactivex.rxkotlin.toObservable


class AirportsActivity : BaseActivity(), AirportsAdapter.OnAirportClickedListener {

    companion object {
        const val SELECTED_AIRPORT_KEY = "selected_airport_key"
    }

    // dummy list of airports instead of adding all world airports
    private val airportsList = listOf(
        Airport("CAI", "Cairo International Airport"),
        Airport("SYD", "Sydney International Airport"),
        Airport("ASW", "Aswan International Airport"),
        Airport("JED", "Jeddah International Airport"),
        Airport("RUH", "Riyadh International Airport"),
        Airport("MED", "Madinah International Airport")
    )
    private lateinit var adapter: AirportsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airports)
        initUI()
    }

    private fun initUI() {
        initilizeToolbar()
        initilizeRecyclerView()
        setSearchViewListener()
    }

    private fun setSearchViewListener() {
        searchViewAirport.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //when traveller searches, the list will be filled be the airports that match the searched airport name
                newText?.isEmpty().also {
                    airportsList.toObservable().filter {
                        it.name.toLowerCase().contains(newText!!.toLowerCase())
                    }
                    val filteredAirportAdapter =
                        AirportsAdapter(airportsList, this@AirportsActivity)
                    recyclerViewAirports.setAdapter(filteredAirportAdapter)
                } ?: run {
                    //if search text is null or empty return default list of airports
                    val airportAdapter =
                        AirportsAdapter(airportsList, this@AirportsActivity)
                    recyclerViewAirports.setAdapter(airportAdapter)
                }
                return true
            }
        })
    }

    private fun initilizeRecyclerView() {
        adapter = AirportsAdapter(airportsList, this)
        recyclerViewAirports.layoutManager = LinearLayoutManager(this)
        recyclerViewAirports.setAdapter(adapter)
    }

    private fun initilizeToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true);
            setDisplayShowHomeEnabled(true);
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun setOnAirportClicked(airport: Airport) {
        Intent().putExtra(SELECTED_AIRPORT_KEY, airport).also {
            setResult(RESULT_OK, it)
            finish()
        }
    }

    override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.airport_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchViewAirport.setMenuItem(item)
        return true
    }
}
