package com.example.walutki.screens.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R

class CurrenciesViewHolder(v: View): RecyclerView.ViewHolder(v){

    val currentName = v.findViewById<TextView>(R.id.currentName)!!
    val currentPercent = v.findViewById<TextView>(R.id.currentPercent)!!
    val currentValue = v.findViewById<TextView>(R.id.currentValue)!!
    val countryImage = v.findViewById<ImageView>(R.id.countryImage)!!
    val starIcon = v.findViewById<ImageView>(R.id.starImage)!!

}