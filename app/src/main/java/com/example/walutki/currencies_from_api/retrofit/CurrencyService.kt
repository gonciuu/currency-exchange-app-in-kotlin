package com.example.walutki.currencies_from_api.retrofit

import com.example.walutki.currencies_from_api.obj_class.Currency
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {

    //GET API SHORT URL
    @GET("latest?base=USD")
    fun getCurrencyAsync(): Deferred<Response<Currency>>
}