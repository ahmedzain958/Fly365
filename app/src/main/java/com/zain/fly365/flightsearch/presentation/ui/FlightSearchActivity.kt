package com.zain.fly365.flightsearch.presentation.ui

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.zain.fly365.R
import com.zain.fly365.base.ui.BaseActivity
import com.zain.fly365.flightsearch.presentation.ui.adapter.FlightSearchFragmentsAdapter
import kotlinx.android.synthetic.main.activity_flight_search.*
import kotlinx.android.synthetic.main.fly365_toolbar.*

class FlightSearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_search)
        initUI()
    }

    //initialize user interface
    private fun initUI() {
        initToolbar(flyToolbar, getString(R.string.flights), false)
        initTabLayout()
    }

    private fun initTabLayout() {
        tabLayout?.apply {
            //add tabs text names
            addTab(newTab().setText(getString(R.string.one_way)))
            addTab(newTab().setText(getString(R.string.round_trip)))
            addTab(newTab().setText(getString(R.string.multi_city)))
            viewPager?.let { viewPager ->
                //setup view pager adapter
                viewPager.adapter = FlightSearchFragmentsAdapter(supportFragmentManager, tabCount)
                viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(this))
                //match tablayout selected position to the view pager current item
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab) {
                        viewPager.currentItem = tab.position
                    }
                    override fun onTabUnselected(tab: TabLayout.Tab) {}
                    override fun onTabReselected(tab: TabLayout.Tab) {}
                })
            }
        }
    }

}
