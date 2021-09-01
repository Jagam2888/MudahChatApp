package com.jagad.mudahchatapp.network

import android.content.Context
import com.jagad.mudahchatapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Created by jagad on 8/10/2021
 */

class RetrofitClientInstance @Inject constructor() {
    private fun createOkHttp(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(InternetConnectionInterceptor(context))
            .build()
    }

    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .client(createOkHttp(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    class InternetConnectionInterceptor(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!CheckInternetConnectivity.isAvailable(context))
                throw NoInternetException(CheckInternetConnectivity.errorMsg)

            return chain.proceed(chain.request())
        }
    }
}