package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Sprite : CocktailInterface {
    override val name: String = "Sprite"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.sprite
    init {
        ingredients["Sprite"] = 160
        extraIngredients["Ice"] = 5
    }
}