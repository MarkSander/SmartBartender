package com.example.smartbartender

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CocktailViewModel() : ViewModel() {
    //private val dataStore = context.dataStore
    val cocktailList: MutableList<CocktailInterface> = mutableListOf()


    fun addNewCocktail(cocktail: CocktailInterface) {
        val nonEmptyIngredients = cocktail.filterNonEmptyIngredients()
        if (nonEmptyIngredients.isNotEmpty() && cocktail.name.isNotEmpty()) {
            cocktailList.add(Cocktail(cocktail.name, nonEmptyIngredients, cocktail.extraIngredients, cocktail.imageResourceId))
        }
    }

    private fun CocktailInterface.filterNonEmptyIngredients(): MutableMap<String, Int> {
        val filteredIngredients = mutableMapOf<String, Int>()
        for ((ingredient, quantity) in this.ingredients) {
            if (!ingredient.contains("Empty", ignoreCase = true)) {
                filteredIngredients[ingredient] = quantity
            }
        }
        return filteredIngredients
    }
}

