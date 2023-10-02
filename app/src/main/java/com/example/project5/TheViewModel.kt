package com.example.project5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project5.databinding.Fragment1Binding

class TheViewModel : ViewModel() {
     val bindingFrag: MutableLiveData<Fragment1Binding> = MutableLiveData()
}