package com.example.project5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project5.databinding.ActivityMainBinding
import com.example.project5.databinding.Fragment1Binding

class Fragment1: Fragment() {
    private lateinit var binding: Fragment1Binding

    private lateinit var viewModel: TheViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * By overwriting onCreateView we are able to inflate with the given viewGroup and the id's they hold
         * The results from the user's input and displaying it on the screen
         *
         * @param LayoutInflater inflater
         * @param ViewGroup container
         * @param savedInstanceState Bundle
         * @return the inflated view
         */
        // Inflate the layout for this fragment
        val binding = Fragment1Binding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(TheViewModel::class.java)
        viewModel.bindingFrag.value = binding
        return binding.root
    }
}