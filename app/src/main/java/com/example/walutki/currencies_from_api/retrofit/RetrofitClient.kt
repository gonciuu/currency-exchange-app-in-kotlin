package com.example.walutki.currencies_from_api.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //---------API RETROFIT BUILDER-----------
    val instance: CurrencyService by lazy {

        val retrofit = Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(
                OkHttpClient.Builder().build()
            ).build()

        retrofit.create(CurrencyService::class.java)

    }
    //========================================
}