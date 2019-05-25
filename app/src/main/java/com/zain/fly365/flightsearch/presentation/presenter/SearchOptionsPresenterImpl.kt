package com.zain.fly365.flightsearch.presentation.presenter

import com.zain.fly365.flightsearch.domain.InsertTravellerSearchOptionsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchOptionsPresenterImpl constructor(
    val insertTravellerSearchOptionsUseCase: InsertTravellerSearchOptionsUseCase,
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

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}