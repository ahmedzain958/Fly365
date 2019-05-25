package com.zain.fly365.base.data.remote.di

import com.google.gson.GsonBuilder
import com.zain.fly365.BuildConfig
import com.zain.fly365.base.data.remote.RemoteConstants
import com.zain.fly365.base.data.remote.interceptor.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val DEFAULT_RETROFIT = "Default_Retrofit"
private const val DEFAULT_OK_HTTP = "Default_ok_http"
@JvmField
val remoteModule = module {

    single { HeadersInterceptor() }

    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

    single<OkHttpClient> {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(get<HeadersInterceptor>())
            .connectTimeout(RemoteConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RemoteConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RemoteConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(get<HttpLoggingInterceptor>())
        }

        builder.build()
    }

    single { GsonBuilder().serializeNulls().create() }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
}
