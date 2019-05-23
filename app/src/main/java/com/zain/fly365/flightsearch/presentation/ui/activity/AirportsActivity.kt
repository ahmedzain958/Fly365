package com.zain.fly365.flightsearch.presentation.ui.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.zain.fly365.R
import com.zain.fly365.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_airports.*
import android.widget.ArrayAdapter
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable


class AirportsActivity : BaseActivity() {
    private data class Airport(val code: String, val name: String)

    // dummy list of airports instead of adding all world airports
    private val airportsList = listOf(
        Airport("CAI", "Cairo International Airport"),
        Airport("SYD", "Sydney International Airport"),
        Airport("ASW", "Aswan International Airport"),
        Airport("JED", "Jeddah International Airport"),
        Airport("RUH", "Riyadh International Airport"),
        Airport("MED", "Madinah International Airport")
    )
    val airportsNamesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airports)
        //get airport names to fill the list by all default airports
        airportsList.toObservable().map { it.name }.subscribeBy { airportsNamesList.add(it) }
        initUI()
    }

    private fun initUI() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true);
            setDisplayShowHomeEnabled(true);
        }
        val adapter =
            ArrayAdapter(this@AirportsActivity, android.R.layout.simple_list_item_1, airportsNamesList)
        listViewAirports.setAdapter(adapter)

        /*would like to filter list using rxjava but searchViewAirport which
         is of type MaterialSearchView extends FrameLayout not ordinary textview*/
        searchViewAirport.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //when traveller searches, the list will be filled be the airports names that match the searched airport name
                newText?.isEmpty().also {
                    val filteredList = mutableListOf<String>()
                    airportsList.toObservable().filter {
                        it.name.toLowerCase().contains(newText!!.toLowerCase())
                    }.subscribeBy { filteredList.add(it.name) }
                    val airportsNamesAdapter =
                        ArrayAdapter(this@AirportsActivity, android.R.layout.simple_list_item_1, filteredList)
                    listViewAirports.setAdapter(airportsNamesAdapter)
                } ?: run {
                    //if search text is null or empty return default list of airports
                    val airportsNamesAdapter =
                        ArrayAdapter(this@AirportsActivity, android.R.layout.simple_list_item_1, airportsNamesList)
                    listViewAirports.setAdapter(airportsNamesAdapter)
                }
                return true
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override
    fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.airport_menu, menu)
        val item = menu.findItem(R.id.action_search)
        searchViewAirport.setMenuItem(item)
        return true
    }
}
