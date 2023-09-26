package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class DraculaKiss : CocktailInterface {
    override val name: String = "Dracula's Kiss"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.draculakiss
    init {
        ingredients["Vodka"] = 60
        ingredients["Coke"] = 120
        extraIngredients["Ice"] = 4
        extraIngredients["Lime"] = 2
    }
}