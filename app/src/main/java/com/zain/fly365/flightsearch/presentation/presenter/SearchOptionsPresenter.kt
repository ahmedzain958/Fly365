package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.base.presenter.BasePresenter
import com.zain.fly365.flightsearch.data.CabinClassEnum

interface SearchOptionsPresenter : BasePresenter {
    fun insertTravellerSearchOptions(cabinClassSelectedValue: Int, adultCount: Int, childCount: Int, infantCount: Int)
    fun getTravellersNumber(): Int
    fun getAdultsNumber(): Int
    fun getChildrenNumber(): Int
    fun getInfantsNumber(): Int
    fun getSelectedCabinClass(): CabinClassEnum
}