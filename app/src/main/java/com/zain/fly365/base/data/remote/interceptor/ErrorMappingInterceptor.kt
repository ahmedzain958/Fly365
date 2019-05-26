package com.zain.fly365.base.data.remote.interceptor

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.zain.fly365.base.data.exception.APIException
import com.zain.fly365.base.data.exception.Errors
import com.zain.fly365.base.data.exception.NetworkException
import com.zain.fly365.base.data.resources.repository.ResourcesRepository
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.net.SocketTimeoutException
import java.nio.charset.Charset

class ErrorMappingInterceptor(
    private val gsonParser: Gson,
    private val resourcesRepository: ResourcesRepository
) : Interceptor {

    private val keyJson = "json"

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response: Response

        try {
            response = chain.proceed(request)
        } catch (e: IOException) {
            if (e is SocketTimeoutException) {
                throw NetworkException(resourcesRepository.getSocketTimeoutExceptionMessage())
            } else {
                throw NetworkException(resourcesRepository.getNetworkExceptionMessage())
            }
        }
        val body = response.body()!!
        if (isJsonTypeResponse(body)) {
            val source = body.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            val charset = body.contentType()!!.charset(Charset.forName("UTF-8"))!!
            val responseBody = buffer.clone().readString(charset)
            try {
                val apiException = gsonParser.fromJson(responseBody, APIException::class.java)
                apiException.code = response.code()
                if (apiException.errors?.legsOrigin == null) {
                    apiException.message = resourcesRepository.getGenericUnknownErrorMessage()
                    throw apiException

                }
            } catch (e: JsonSyntaxException) {
                throw APIException(
                    response.code(), Errors(listOf(resourcesRepository.getGenericUnknownErrorMessage())),
                    resourcesRepository.getGenericUnknownErrorMessage(),
                    response.code(),
                    resourcesRepository.getGenericUnknownErrorMessage(),
                    "",
                    ""
                )
            }

        }
        return response
    }

    private fun isJsonTypeResponse(body: ResponseBody?): Boolean {
        return body?.contentType() != null && body.contentType()!!.subtype().toLowerCase() == keyJson
    }
}