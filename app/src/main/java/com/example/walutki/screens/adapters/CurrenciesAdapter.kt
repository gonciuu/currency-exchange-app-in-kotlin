package com.example.walutki.screens.adapters

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlin.math.abs

class CurrenciesAdapter(private val currencies:HashMap<String,Double>,private val lastCurrencies:HashMap<String,Double>, private val listOfCurrenciesSymbols:ArrayList<String>,private val likedList:ArrayList<String>,private val sp:SharedPreferences) : RecyclerView.Adapter<CurrenciesViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        return CurrenciesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.current_card,parent,false))
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.currentName.text = listOfCurrenciesSymbols[holder.adapterPosition]
        holder.currentValue.text  = String.format("%.4f",currencies[listOfCurrenciesSymbols[holder.adapterPosition]])
        Picasso.get().load("https://www.countryflags.io/${listOfCurrenciesSymbols[holder.adapterPosition][0] + "" + listOfCurrenciesSymbols[holder.adapterPosition][1]}/flat/64.png").into(holder.countryImage)

        val diffrent = 100 - (lastCurrencies[listOfCurrenciesSymbols[holder.adapterPosition]]!! * 100) / (currencies[listOfCurrenciesSymbols[holder.adapterPosition]]!!)
        when {
            diffrent>0 -> {holder.currentPercent.setTextColor(Color.parseColor("#db0f00"))}
            diffrent<0 -> {holder.currentPercent.setTextColor(Color.parseColor("#00e81f"))}
            else -> {holder.currentPercent.setTextColor(Color.parseColor("#0092db"))}
        }
        holder.currentPercent.text = String.format("%.2f", abs(diffrent)) + "%"

        if(likedList.contains(listOfCurrenciesSymbols[holder.adapterPosition])){ holder.starIcon.setImageResource(R.drawable.ic_star) }
        else{ holder.starIcon.setImageResource(R.drawable.ic_star_border) }

        holder.starIcon.setOnClickListener {
            if(likedList.contains(listOfCurrenciesSymbols[holder.adapterPosition])){
                holder.starIcon.setImageResource(R.drawable.ic_star_border)
                likedList.remove(listOfCurrenciesSymbols[holder.adapterPosition])
            }else{
                holder.starIcon.setImageResource(R.drawable.ic_star)
                likedList.add(listOfCurrenciesSymbols[holder.adapterPosition])
            }
            sp.edit().apply {
                putString("likedList", Gson().toJson(likedList))
                apply()
            }
        }

    }
}


