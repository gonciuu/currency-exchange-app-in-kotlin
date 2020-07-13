package com.example.walutki.currencies_from_api.retrofit

import com.example.walutki.currencies_from_api.obj_class.Currency
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyService {

    //GET API SHORT URL
    @GET("latest?base=USD")
    fun getCurrencyAsync(): Deferred<Response<Currency>>

    //GET YESTERDAY API SHORT URL
    @GET("{date}?base=USD")
    fun getCurrencyHistoryAsync(@Path("date") date:String): Deferred<Response<Currency>>
}