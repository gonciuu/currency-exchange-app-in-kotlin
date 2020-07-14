package com.example.walutki.screens.adapters.currencies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R
import kotlinx.android.synthetic.main.current_card.view.*

class CurrenciesViewHolder(v: View): RecyclerView.ViewHolder(v){

    val currentName = v.currentName!!
    val currentPercent = v.currentPercent!!
    val currentValue = v.currentValue!!
    val countryImage = v.countryImage!!
    val starIcon = v.starImage!!

}