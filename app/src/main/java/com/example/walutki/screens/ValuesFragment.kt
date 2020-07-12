package com.example.walutki.screens

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


    private fun getCurrencies(){
        loadingViewModel.getCurrency().observe(viewLifecycleOwner, Observer {
            Log.d("ALERT",it["PLN"].toString())
            setAdapter(it)
        })
    }


    private fun setAdapter(currencies : HashMap<String,Double>){
        val listOfCurrenciesSymbols = arrayListOf<String>()
        for(symbol in currencies.keys){
            listOfCurrenciesSymbols.add(symbol)
        }
        currentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrenciesAdapter(currencies,listOfCurrenciesSymbols)
        }
    }

}