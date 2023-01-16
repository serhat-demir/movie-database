package com.serhat.moviedatabase.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "PUT_YOUR_API_KEY_HERE"

        fun getClient(): ApiInterface {
            val client = OkHttpClient.Builder()
            client.addInterceptor { chain ->
                val url = chain.request().url().newBuilder().addQueryParameter("api_key", API_KEY).build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}