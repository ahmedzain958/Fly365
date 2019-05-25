package com.zain.fly365.flightsearch.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.zain.fly365.R
import com.zain.fly365.flightsearch.data.CabinClass
import com.zain.fly365.flightsearch.presentation.presenter.SearchOptionsPresenter
import com.zain.fly365.flightsearch.presentation.ui.adapter.SearchOptionsAdapter
import kotlinx.android.synthetic.main.activity_search_options.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchOptionsActivity : AppCompatActivity(), SearchOptionsAdapter.ItemClickListener {

    private lateinit var adapter: SearchOptionsAdapter
    private var adultCount = 0
    private var childCount = 0
    private var infantCount = 0
    private var cabinClassSelectedValue: Int = 0
    private val searchOptionsPresenter: SearchOptionsPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_options)
        initUI()
    }

    private fun initUI() {
        val numberOfColumns = 2
        recyclerViewCabinTypes.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SearchOptionsAdapter(CabinClass.values(), this)
        recyclerViewCabinTypes.adapter = adapter
        setIncrementAndDecrementClickListeners()
        //set searchOptions default values cabinClassSelectedValue=0,adultCount=0,childCount=0,infantCount=0
        searchOptionsPresenter.insertTravellerSearchOptions(
            cabinClassSelectedValue,
            adultCount,
            childCount,
            infantCount
        )
        buttonApply.setOnClickListener {
            searchOptionsPresenter.insertTravellerSearchOptions(
                cabinClassSelectedValue,
                adultCount,
                childCount,
                infantCount
            )
            Intent().also {
                setResult(RESULT_OK, it)
                finish()
            }
        }
    }

    private fun setIncrementAndDecrementClickListeners() {
        buttonIncrementAdult.setOnClickListener {
            val quantity = editTextQuantityAdult.text.toString().toInt()
            adultCount = quantity.inc()
            editTextQuantityAdult.setText(adultCount.toString())
        }
        buttonDecrementAdult.setOnClickListener {
            val quantity = editTextQuantityAdult.text.toString().toInt()
            if (quantity != 0) {
                adultCount = quantity.dec()
                editTextQuantityAdult.setText(adultCount.toString())
            }
        }
        buttonIncrementChild.setOnClickListener {
            val quantity = editTextQuantityChild.text.toString().toInt()
            childCount = quantity.inc()
            editTextQuantityChild.setText(childCount.toString())
        }
        buttonDecrementChild.setOnClickListener {
            val quantity = editTextQuantityChild.text.toString().toInt()
            if (quantity != 0) {
                childCount = quantity.dec()
                editTextQuantityChild.setText(childCount.toString())
            }
        }
        buttonIncrementInfant.setOnClickListener {
            val quantity = editTextQuantityInfant.text.toString().toInt()
            infantCount = quantity.inc()
            editTextQuantityInfant.setText(infantCount.toString())
        }
        buttonDecrementInfant.setOnClickListener {
            val quantity = editTextQuantityInfant.text.toString().toInt()
            if (quantity != 0) {
                infantCount = quantity.dec()
                editTextQuantityInfant.setText(infantCount.toString())
            }
        }
    }

    override fun onItemClick(view: View, adapterPosition: Int) {
        val cabinClasses: Array<CabinClass> = CabinClass.values()
        //clear all selected values from the list to avoid multi select
        cabinClasses.map {
            it.isSelected = false
        }
        cabinClasses.get(adapterPosition).isSelected = true
        adapter = SearchOptionsAdapter(cabinClasses, this)
        recyclerViewCabinTypes.adapter = adapter
        adapter.notifyDataSetChanged()
        cabinClassSelectedValue = adapterPosition
    }

}
