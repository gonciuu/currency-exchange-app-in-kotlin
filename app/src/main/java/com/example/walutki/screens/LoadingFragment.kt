package com.example.walutki.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.walutki.R
import com.example.walutki.currencies_from_api.retrofit.RetrofitClient
import com.example.walutki.dialogs.ShowMessageDialog
import com.example.walutki.screens.view_models.LoadingViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class LoadingFragment : Fragment() {


    private lateinit var loadingViewModel: LoadingViewModel


    companion object {
        fun newInstance() = LoadingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.loading_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingViewModel = ViewModelProvider(requireActivity()).get(LoadingViewModel::class.java)
        setStartLiked()
        getCurrencies()
    }


    //--------------------IF LIST OF LIKED NOT CONTAINS IN SHARED PREFS SET DEFAULT LIKED CURRENCIES------------------
    private fun setStartLiked(){
        val startList = arrayListOf<String>("PLN","EUR","HRK")
        val sp = requireActivity().getSharedPreferences("LIKED", Context.MODE_PRIVATE)
        if(sp.getString("likedList","")==""){
            sp.edit().apply {
                putString("likedList", Gson().toJson(startList))
                apply()
            }
        }
    }
    //================================================================================================================

    //---------------------------GET CURRENCY RESPONSE FROM API USING RETROFIT-------------------------------

    private fun getCurrencies(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = RetrofitClient.instance.getCurrencyAsync().await().body()!!
                val lastChangeResult = RetrofitClient.instance.getCurrencyHistoryAsync(getYesterday()).await().body()!!
                requireActivity().runOnUiThread {
                    loadingViewModel.setCurrency(result)//today
                    loadingViewModel.setLastCurrency(lastChangeResult)//yesterday
                    findNavController().navigate(R.id.action_loadingFragment_to_valuesFragment)
                }
            }catch (socketEx: SocketTimeoutException){
                Log.d("Error","Check your internet connection")        //no internet connection
                ShowMessageDialog("Intenet","Proszę sprawdzić swoje połączenie z siecią i spróbować ponownie później!").show(requireActivity().supportFragmentManager,"internet_error_tag")
            }catch (unEx:java.net.UnknownHostException){
                Log.d("Error","Check your internet connection. ")
                ShowMessageDialog("Intenet","Proszę sprawdzić swoje połączenie z siecią i spróbować ponownie później!").show(requireActivity().supportFragmentManager,"internet_error_tag2")
            }catch (ex:Exception){
                ShowMessageDialog("Error","Wystąpił nieoczekiwany błąd. Spróbuj ponownie później. ${ex.message}").show(requireActivity().supportFragmentManager,"error_tag")
            }
        }
    }

    //=========================================================================================================


    //-------------------GET YESTERDAY DATE AS STRING---------------------
    @SuppressLint("SimpleDateFormat")
    private fun getYesterday() : String{
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, - 2)
        return dateFormat.format(cal.time)
    }
    //====================================================================
}