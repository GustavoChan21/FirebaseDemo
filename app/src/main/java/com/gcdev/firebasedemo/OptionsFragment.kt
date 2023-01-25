package com.gcdev.firebasedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class OptionsFragment : Fragment(R.layout.fragment_options) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var btnInsertData = requireView().findViewById<Button>(R.id.btnInsertData)
        var btnFetchData = requireView().findViewById<Button>(R.id.btnFetchData)

        btnInsertData.setOnClickListener{
            findNavController().navigate(R.id.action_optionsFragment_to_insertFragment)
        }

        btnFetchData.setOnClickListener{
            findNavController().navigate(R.id.action_optionsFragment_to_fetchFragment)
        }

    }
}