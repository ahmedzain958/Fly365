package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.flightsearch.data.CabinClass
import com.zain.fly365.flightsearch.domain.*
import io.reactivex.disposables.CompositeDisposable

class SearchOptionsPresenterImpl constructor(
    val insertTravellerSearchOptionsUseCase: InsertTravellerSearchOptionsUseCase,
    val getTravellersNumberUseCase: GetTravellersNumberUseCase,
    val getAdultsNumberUseCase: GetAdultsNumberUseCase,
    val getChildrenNumberUseCase: GetChildrenNumberUseCase,
    val getInfantsNumberUseCase: GetInfantsNumberUseCase,
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

    override fun getTravellersNumber(): Int {
        return getTravellersNumberUseCase.execute(null)
    }

    override fun getSelectedCabinClass(): CabinClass {
        return getCabinClassUseCase.execute(null)
    }
    override fun getAdultsNumber(): Int {
      return  getAdultsNumberUseCase.execute(null)
    }

    override fun getChildrenNumber(): Int {
        return  getChildrenNumberUseCase.execute(null)
    }

    override fun getInfantsNumber(): Int {
        return  getInfantsNumberUseCase.execute(null)
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}