package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.base.presenter.BasePresenter
import com.zain.fly365.flightsearch.data.CabinClass
import com.zain.fly365.flightsearch.entities.Airport

interface SearchOptionsPresenter : BasePresenter {
    fun insertTravellerSearchOptions(cabinClassSelectedValue: Int, adultCount: Int, childCount: Int, infantCount: Int)
    fun getTravellersNumberUseCase(): Int
    fun getSelectedCabinClass(): CabinClass
}