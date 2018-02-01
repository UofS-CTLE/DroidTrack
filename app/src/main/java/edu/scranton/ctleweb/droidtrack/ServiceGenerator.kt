package edu.scranton.ctleweb.droidtrack

import android.text.TextUtils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val API_BASE_URL = "https://ctleweb.scranton.edu/projtrack3/"

    private val httpClient = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    private val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>, authToken: String?): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken)

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)

                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }

        return retrofit!!.create(serviceClass)
    }
}