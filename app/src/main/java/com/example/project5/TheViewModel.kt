package com.example.project5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project5.databinding.Fragment1Binding

class TheViewModel : ViewModel() {
     /**
      * Creating the constructor and initializing the binding for the live data for the ml kit
      * @returns view
      */
     val bindingFrag: MutableLiveData<Fragment1Binding> = MutableLiveData()
}