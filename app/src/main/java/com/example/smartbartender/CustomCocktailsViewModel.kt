package com.example.smartbartender

import androidx.lifecycle.ViewModel

class CustomCocktailsViewModel: ViewModel() {
    var newCocktails: MutableList<Cocktail> = mutableListOf()
}