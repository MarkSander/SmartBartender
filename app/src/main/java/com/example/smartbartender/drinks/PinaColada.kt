package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class PinaColada : CocktailInterface {
    override val name: String = "Pina Colada"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.pinacoladacocktail
    init {
        ingredients["Witte rum"] = 45
        ingredients["Kokosmelk"] = 30
        ingredients["Ananassap"] = 90
        extraIngredients["Ananas"] = 1
    }
}