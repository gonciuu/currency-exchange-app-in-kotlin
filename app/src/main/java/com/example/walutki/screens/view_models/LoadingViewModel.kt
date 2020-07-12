package com.example.walutki.screens.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walutki.currencies_from_api.obj_class.Currency

class LoadingViewModel : ViewModel() {
    private val currency = MutableLiveData<HashMap<String,Double>>()

    fun setCurrency(userCurrency: Currency){
        currency.value = setCurrencies(userCurrency)
    }

    fun getCurrency() :LiveData<HashMap<String,Double>> = currency


    private fun setCurrencies(userCurrency: Currency) : HashMap<String,Double>{
        val map = HashMap<String,Double>()
        map["PLN"] = userCurrency.rates.pLN
        map["EUR"] = userCurrency.rates.eUR
        map["USD"] = userCurrency.rates.uSD
        map["HRK"] = userCurrency.rates.hRK //kuna horwacka
        map["JPY"] = userCurrency.rates.jPY //jen japonski
        map["GBP"] = userCurrency.rates.gBP //funt
        map["AUD"] = userCurrency.rates.aUD //dolar australijski
        map["CHF"] = userCurrency.rates.cHF //frank
        map["CAD"] = userCurrency.rates.cAD //dalar kanadysjki
        map["CZK"] = userCurrency.rates.cZK //korona czeska
        return  map
    }
}