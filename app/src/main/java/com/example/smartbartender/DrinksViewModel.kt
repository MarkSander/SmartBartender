package com.example.smartbartender

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DrinksViewModel: ViewModel() {
    //var inputDrinks: List<String> = emptyList()
    val drinkInput1 = MutableLiveData<String>().apply { value = "Empty" }
    val drinkInput2 = MutableLiveData<String>().apply { value = "Empty" }
    val drinkInput3 = MutableLiveData<String>().apply { value = "Empty" }
    val drinkInput4 = MutableLiveData<String>().apply { value = "Empty" }

}