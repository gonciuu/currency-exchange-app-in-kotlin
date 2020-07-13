package com.example.walutki.screens

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walutki.R
import com.example.walutki.screens.adapters.CurrenciesAdapter
import com.example.walutki.screens.view_models.LoadingViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_values.*


class ValuesFragment : Fragment() {

    private lateinit var loadingViewModel:LoadingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_values, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingViewModel = ViewModelProvider(requireActivity()).get(LoadingViewModel::class.java)

        calculateButton.setOnClickListener {
            findNavController().navigate(R.id.action_valuesFragment_to_calculateFragment)
        }

        getCurrencies()

    }


    //-------------------------------------GET CURRENCIES FROM VIEWMODEL AND LIKED CURRENCIES FROM HARED PREFS--------------------------------------
    private fun getCurrencies(){
        loadingViewModel.getCurrency().observe(viewLifecycleOwner, Observer {
            arleady->
                loadingViewModel.getLastCurrency().observe(viewLifecycleOwner, Observer {
                    last->
                    val likedList = Gson().fromJson(requireActivity().getSharedPreferences("LIKED", MODE_PRIVATE).getString("likedList",""),ArrayList::class.java) as ArrayList<String>
                    Log.d("ALERT",arleady["PLN"].toString())
                    Log.d("ALERT",likedList.toString())
                    Log.d("ALERT",last["PLN"].toString())
                    setAdapter(arleady,last,likedList,requireActivity().getSharedPreferences("LIKED", MODE_PRIVATE))
                })
        })
    }
    //===============================================================================================================================================





    //-------------------------------------------------------SET ADAPTER ON RECYCLER VIEW-----------------------------------------------------------
    private fun setAdapter(currencies : HashMap<String,Double>,lastCurrencies : HashMap<String,Double>,likedList:ArrayList<String>,sp:SharedPreferences){
        val listOfCurrenciesSymbols = arrayListOf<String>()
        for(symbol in currencies.keys){
            listOfCurrenciesSymbols.add(symbol)     //GET ALL CURRENCIES SYMBOLS
        }
        currentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrenciesAdapter(context,currencies,lastCurrencies,listOfCurrenciesSymbols,likedList,sp)
        }
    }
    //===============================================================================================================================================

}