package com.gcdev.firebasedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class FetchFragment : Fragment(R.layout.fragment_fetch) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnReturnMenu = requireView().findViewById<Button>(R.id.button_returnMenu)

        btnReturnMenu.setOnClickListener{
            findNavController().navigate(R.id.action_fetchFragment_to_optionsFragment)
        }
    }
}