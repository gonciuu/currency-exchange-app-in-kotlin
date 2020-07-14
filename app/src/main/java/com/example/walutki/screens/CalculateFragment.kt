package com.example.walutki.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walutki.R
import com.example.walutki.screens.adapters.calculate_currencies.CalculateCurrenciesAdapter
import com.example.walutki.screens.view_models.LoadingViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_calculate.*
import java.text.SimpleDateFormat
import java.util.*


class CalculateFragment : Fragment() {

    private lateinit var loadingViewModel: LoadingViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingViewModel = ViewModelProvider(requireActivity()).get(LoadingViewModel::class.java)

        getCurrencies()
        setDate()
    }


    private fun getCurrencies() {
        finalValue.isSelected = true
        loadingViewModel.getCurrency().observe(viewLifecycleOwner, Observer {
            setSpinners(it)
            calculateOnTextChange(it)
        })
    }


    private fun setSpinners(currencies: HashMap<String, Double>) {
        val firstCurrentAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            currencies.keys.toList()
        )
        firstCurrentSpinner.adapter = firstCurrentAdapter

        val secondCurrentAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, currencies.keys.toList())
        secondCurrentSpinner.adapter = secondCurrentAdapter

        setSpinnersOnClick(currencies)
    }


    private fun setSpinnersOnClick(currencies: HashMap<String, Double>) {
        firstCurrentSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                setFlagImage(selectedItem, firstCurrentFlag)
                setCurrentValue(currencies[selectedItem]!!,currencies[secondCurrentSpinner.selectedItem.toString()]!!)
                calculateCurrencies(currencies[selectedItem]!!,currencies[secondCurrentSpinner.selectedItem.toString()]!!,try { firstCurrentValue.text.toString().toInt() }catch (ex:Exception){ 0 })
                setCurrenciesAdapter(currencies)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        secondCurrentSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                setFlagImage(selectedItem, secondCurrentFlag)
                setCurrentValue(currencies[firstCurrentSpinner.selectedItem.toString()]!!,currencies[selectedItem]!!)
                calculateCurrencies(currencies[firstCurrentSpinner.selectedItem.toString()]!!,currencies[selectedItem]!!,try{firstCurrentValue.text.toString().toInt()}catch (ex:Exception){0})
                setCurrenciesAdapter(currencies)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    private fun setFlagImage(countryCode: String, imageView: ImageView) = Picasso.get()
        .load("https://www.countryflags.io/${countryCode[0] + "" + countryCode[1]}/flat/64.png")
        .fit().centerCrop().into(imageView)


    private fun setDate(){
        dateTV.text =  SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(Calendar.getInstance().timeInMillis)
    }


    private fun setCurrentValue(firstSpinnerValue:Double,secondSpinnerValue:Double){
        try{
            currentValue.text = String.format("%.4f",secondSpinnerValue / firstSpinnerValue)
        }catch (nbEx:java.lang.NumberFormatException){
            Toast.makeText(context,"Number format exception handled",Toast.LENGTH_LONG).show()
        }catch (ex:Exception){ }
    }


    private fun calculateOnTextChange(currencies: HashMap<String, Double>){
        firstCurrentValue.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                calculateCurrencies(currencies[firstCurrentSpinner.selectedItem.toString()]!!,currencies[secondCurrentSpinner.selectedItem.toString()]!!,try {
                    firstCurrentValue.text.toString().toInt()
                }catch (ex:Exception){
                    0
                })
                setCurrenciesAdapter(currencies)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }


    private fun calculateCurrencies(firstSpinnerValue:Double,secondSpinnerValue:Double,quantity:Int){
        try{
            val value= String.format("%.1f",(secondSpinnerValue / firstSpinnerValue) * quantity)
            finalValue.text = value
        }catch (NbEx:NumberFormatException){
            Toast.makeText(context,"Wrong text in field",Toast.LENGTH_SHORT).show()
        }
    }


    private fun setCurrenciesAdapter(currencies: HashMap<String, Double>){
        val listOfCurrenciesSymbols = arrayListOf<String>()
        for(symbol in currencies.keys){
            listOfCurrenciesSymbols.add(symbol)     //GET ALL CURRENCIES SYMBOLS
        }
        calculateRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CalculateCurrenciesAdapter(currencies,listOfCurrenciesSymbols)
        }
    }


}