package com.example.walutki.screens.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R
import com.squareup.picasso.Picasso

class CurrenciesAdapter(private val currencies:HashMap<String,Double>, private val listOfCurrenciesSymbols:ArrayList<String>) : RecyclerView.Adapter<CurrenciesViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        return CurrenciesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.current_card,parent,false))
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.currentName.text = listOfCurrenciesSymbols[holder.adapterPosition]
        holder.currentValue.text  = String.format("%.2f",currencies[listOfCurrenciesSymbols[holder.adapterPosition]])
        Picasso.get().load("https://www.countryflags.io/${listOfCurrenciesSymbols[holder.adapterPosition][0] + "" + listOfCurrenciesSymbols[holder.adapterPosition][1]}/flat/64.png").into(holder.countryImage)
    }
}


