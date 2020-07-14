package com.example.walutki.screens.adapters.calculate_currencies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R
import com.squareup.picasso.Picasso

class CalculateCurrenciesAdapter(private val currencies: HashMap<String, Double>, private val listOfCurrenciesSymbols: ArrayList<String>) : RecyclerView.Adapter<CalculateCurrenciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculateCurrenciesViewHolder {
        return CalculateCurrenciesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.current_card, parent, false))
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CalculateCurrenciesViewHolder, position: Int) {
        holder.starIcon.setImageResource(R.drawable.ic_money_exchange)
        holder.currentPercent.visibility = View.GONE
        holder.currentValue.textSize = 22.5f

        holder.currentName.text = listOfCurrenciesSymbols[holder.adapterPosition]
        holder.currentValue.text = String.format("%.4f",currencies[listOfCurrenciesSymbols[holder.adapterPosition]])
        Picasso.get().load("https://www.countryflags.io/${listOfCurrenciesSymbols[holder.adapterPosition][0] + "" + listOfCurrenciesSymbols[holder.adapterPosition][1]}/flat/64.png").into(holder.countryImage)
    }
}