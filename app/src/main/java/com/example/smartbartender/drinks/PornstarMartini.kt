package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class PornstarMartini : CocktailInterface {
    override val name: String = "Pornstar Martini"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.pornstarmartini
    init {
        ingredients["Vodka"] = 50
        ingredients["Passoa"] = 50
        ingredients["Tropical Fruit Juice"] = 80
        extraIngredients["Egg white"] = 1
        extraIngredients["Ice"] = 2
    }
}