package com.example.walutki.screens.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.walutki.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlin.math.abs


class CurrenciesAdapter(private val context: Context,private val currencies:HashMap<String,Double>,private val lastCurrencies:HashMap<String,Double>, private val listOfCurrenciesSymbols:ArrayList<String>,private val likedList:ArrayList<String>,private val sp:SharedPreferences) : RecyclerView.Adapter<CurrenciesViewHolder>() {



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

        val different = 100 - (lastCurrencies[listOfCurrenciesSymbols[holder.adapterPosition]]!! * 100) / (currencies[listOfCurrenciesSymbols[holder.adapterPosition]]!!)
        when {
            different>0 -> {holder.currentPercent.setTextColor(Color.parseColor("#db0f00"))}
            different<0 -> {holder.currentPercent.setTextColor(Color.parseColor("#00e81f"))}
            else -> {holder.currentPercent.setTextColor(Color.parseColor("#0092db"))}
        }
        holder.currentPercent.text = String.format("%.2f", abs(different)) + "%"

        if(likedList.contains(listOfCurrenciesSymbols[holder.adapterPosition])){ holder.starIcon.setImageResource(R.drawable.ic_star) }
        else{ holder.starIcon.setImageResource(R.drawable.ic_star_border) }

        holder.starIcon.setOnClickListener {
            if(likedList.contains(listOfCurrenciesSymbols[holder.adapterPosition])){
                imageViewAnimatedChange(context,holder.starIcon,R.drawable.ic_star_border)
                likedList.remove(listOfCurrenciesSymbols[holder.adapterPosition])
            }else{
                imageViewAnimatedChange(context,holder.starIcon,R.drawable.ic_star)
                likedList.add(listOfCurrenciesSymbols[holder.adapterPosition])
            }
            sp.edit().apply {
                putString("likedList", Gson().toJson(likedList))
                apply()
            }
        }

    }

    private fun imageViewAnimatedChange(c: Context?, v: ImageView, new_image: Int) {
        val animOut: Animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_out)
        val animIn: Animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in)
        animOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                v.setImageResource(new_image)
                animIn.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                v.startAnimation(animIn)
            }
        })
        v.startAnimation(animOut)
    }
}


