package com.jagad.mudahchatapp.network

import android.content.Context
import com.jagad.mudahchatapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jagad on 8/10/2021
 */

fun createOkHttp(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(InternetConnectionInterceptor(context))
        .build()
}

fun RetrofitClientInstance(context: Context) : Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttp(context))
        .build()

}

class InternetConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!CheckInternetConnectivity.isAvailable(context))
            throw NoInternetException(CheckInternetConnectivity.errorMsg)

        return chain.proceed(chain.request())
    }
}