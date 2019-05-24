package com.zain.fly365.flightsearch.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zain.fly365.multicity.presentation.ui.MultiCityFragment
import com.zain.fly365.oneway.presentation.ui.OneWayFragment
import com.zain.fly365.roundtrip.presentation.ui.RoundTripFragment

class FlightSearchFragmentsAdapter(fragmentManager: FragmentManager, private val totalTabsCount: Int) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return OneWayFragment()
            }
            1 -> {
                return RoundTripFragment()
            }
            2 -> {
                return MultiCityFragment()
            }
            else -> return OneWayFragment()
        }
    }

    override fun getCount(): Int = totalTabsCount
}