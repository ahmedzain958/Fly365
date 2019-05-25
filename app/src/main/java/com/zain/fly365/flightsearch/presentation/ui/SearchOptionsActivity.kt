package com.zain.fly365.flightsearch.presentation.ui

import android.annotation.TargetApi
import android.os.Build
import android.os.Build.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.zain.fly365.R
import androidx.recyclerview.widget.GridLayoutManager
import com.zain.fly365.flightsearch.presentation.ui.adapter.SearchOptionsAdapter
import kotlinx.android.synthetic.main.activity_search_options.*
import com.zain.fly365.flightsearch.data.CabinClass


class SearchOptionsActivity : AppCompatActivity(), SearchOptionsAdapter.ItemClickListener {

    private lateinit var adapter: SearchOptionsAdapter
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
    }

    override fun onItemClick(view: View, adapterPosition: Int) {
        val cabinClasses: Array<CabinClass> = CabinClass.values()
        cabinClasses.map {
            it.isSelected = false
        }
        cabinClasses.get(adapterPosition).isSelected = true
        adapter = SearchOptionsAdapter(cabinClasses, this)
        recyclerViewCabinTypes.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}
