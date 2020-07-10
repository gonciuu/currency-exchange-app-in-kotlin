package com.example.walutki.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walutki.R
import com.example.walutki.screens.adapters.CurrentiesAdapter
import kotlinx.android.synthetic.main.fragment_values.*


class ValuesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_values, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculateButton.setOnClickListener {
            findNavController().navigate(R.id.action_valuesFragment_to_calculateFragment)
        }



        currentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrentiesAdapter()
        }
    }

}