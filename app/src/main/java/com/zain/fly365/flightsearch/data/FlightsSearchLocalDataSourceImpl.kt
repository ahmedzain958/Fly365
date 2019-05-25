package com.zain.fly365.flightsearch.data

import com.zain.fly365.base.data.local.AppPreference
import com.zain.fly365.base.data.local.LocalConstants.KEY_PREFERENCE_ADULT_TRAVELLER
import com.zain.fly365.base.data.local.LocalConstants.KEY_PREFERENCE_CABIN_TYPE
import com.zain.fly365.base.data.local.LocalConstants.KEY_PREFERENCE_CHILD_TRAVELLER
import com.zain.fly365.base.data.local.LocalConstants.KEY_PREFERENCE_INFANT_TRAVELLER
import io.reactivex.Completable

class FlightsSearchLocalDataSourceImpl(val appPreference: AppPreference) :
    FlightsSearchLocalDataSource {
    override fun insertSearchOptions(
        cabinClass: Int, adults: Int,
        children: Int, infants: Int
    ) :Completable{
        return Completable.fromCallable{
            appPreference.putInt(KEY_PREFERENCE_CABIN_TYPE, cabinClass)
            appPreference.putInt(KEY_PREFERENCE_ADULT_TRAVELLER, adults)
            appPreference.putInt(KEY_PREFERENCE_CHILD_TRAVELLER, children)
            appPreference.putInt(KEY_PREFERENCE_INFANT_TRAVELLER, infants)
        }

    }
}