package com.example.walutki.screens.adapters.calculate_currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R

class CalculateCurrenciesAdapter() : RecyclerView.Adapter<CalculateCurrenciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculateCurrenciesViewHolder {
        return  CalculateCurrenciesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.current_card,parent,false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CalculateCurrenciesViewHolder, position: Int) {

    }
}