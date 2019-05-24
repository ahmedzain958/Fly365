package com.zain.fly365.base.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(setupRequestHeaders(chain.request()))
    }

    private fun setupRequestHeaders(oldRequest: Request): Request {
        return oldRequest.newBuilder()
            .build()
    }

}
