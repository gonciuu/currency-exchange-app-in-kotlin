package com.example.walutki.screens.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walutki.currencies_from_api.obj_class.Currency

class LoadingViewModel : ViewModel() {
    private val currency = MutableLiveData<Currency>()

    fun setCurrency(userCurrency: Currency){
        currency.value = userCurrency
    }

    fun getCurrency() :LiveData<Currency> = currency
}