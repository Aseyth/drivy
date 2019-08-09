package com.hugoung.drivy.data

import com.google.gson.GsonBuilder
import com.hugoung.drivy.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Set the base configuration of Retrofit
 */
object RetrofitConfiguration {

    /**
     * Interceptor used to log http calls in debug build
     */
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    /**
     * Build OkHttp client and add all interceptor for headers
     */
    private fun buildOkHttpClient(timeout: Long, units: TimeUnit): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(5, TimeUnit.SECONDS)
            writeTimeout(timeout, units)
            readTimeout(timeout, units)
        }.build()
    }

    /**
     * Create [GsonConverterFactory]
     */
    private fun createGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .create()
    )

    /**
     * Initialize the retrofit client with the baseUrl
     * The timeout of a call is set at 5 min
     * The last parameter is the format of the time which is set in MINUTES
     */
    fun create(timeout: Long = 5, units: TimeUnit = TimeUnit.SECONDS): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/2.0/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(buildOkHttpClient(timeout, units))
            .addConverterFactory(createGsonConverterFactory())
            .build()
    }
}