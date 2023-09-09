package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class PornstarMartini : CocktailInterface {
    override val name: String = "Pornstar Martini"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.pornstarmartinicocktail
    init {
        ingredients["Vodka"] = 50
        ingredients["Passoa"] = 50
        ingredients["Passievruchtsap"] = 100
        extraIngredients["Limoen"] = 2
        extraIngredients["Eiwit"] = 2
        extraIngredients["Ice"] = 2
    }
}