package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.flightsearch.data.CabinClass
import com.zain.fly365.flightsearch.domain.GetCabinClassUseCase
import com.zain.fly365.flightsearch.domain.GetTravellersNumberUseCase
import com.zain.fly365.flightsearch.domain.InsertTravellerSearchOptionsUseCase
import io.reactivex.disposables.CompositeDisposable

class SearchOptionsPresenterImpl constructor(
    val insertTravellerSearchOptionsUseCase: InsertTravellerSearchOptionsUseCase,
    val getTravellersNumberUseCase: GetTravellersNumberUseCase,
    val getCabinClassUseCase: GetCabinClassUseCase,
    val compositeDisposable: CompositeDisposable

) : SearchOptionsPresenter {

    override fun insertTravellerSearchOptions(
        cabinClassSelectedValue: Int,
        adultCount: Int,
        childCount: Int,
        infantCount: Int
    ) {
        compositeDisposable.add(
            insertTravellerSearchOptionsUseCase.execute(
                InsertTravellerSearchOptionsUseCase.Params.create(
                    cabinClassSelectedValue, adultCount, childCount, infantCount
                )
            ).subscribe
            {
            }
        )
    }

    override fun getTravellersNumberUseCase(): Int {
        return getTravellersNumberUseCase.execute(null)
    }

    override fun getSelectedCabinClass(): CabinClass {
        return getCabinClassUseCase.execute(null)
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }


}