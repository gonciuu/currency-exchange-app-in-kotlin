package com.example.walutki.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R

class CurrentiesAdapter : RecyclerView.Adapter<CurrenciesRecyclerView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesRecyclerView {
        return CurrenciesRecyclerView(LayoutInflater.from(parent.context).inflate(R.layout.current_card,parent,false))
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: CurrenciesRecyclerView, position: Int) {
    }
}


class CurrenciesRecyclerView(val view:View):RecyclerView.ViewHolder(view){

}