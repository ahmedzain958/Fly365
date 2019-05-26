package com.zain.fly365.base.data.resources.repository

import com.zain.fly365.R
import com.zain.fly365.base.data.resources.AppResources

class ResourcesRepository(private val appResources: AppResources) {

    fun getNetworkExceptionMessage(): String = appResources.getString(R.string.no_internet_connection)

    fun getSocketTimeoutExceptionMessage(): String = appResources.getString(R.string.timeout_error_message)

    fun getGenericUnknownErrorMessage(): String = appResources.getString(R.string.generic_unknown_error)

}