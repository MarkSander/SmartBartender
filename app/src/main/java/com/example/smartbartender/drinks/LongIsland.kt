package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class LongIsland : CocktailInterface {
    override val name: String = "Long Island Ice Tea"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int>
        get() = TODO("Not yet implemented")

    //override val picture: String = "bloody_mary_picture.jpg" // You can specify the actual picture file path here
    override val imageResourceId: Int = R.drawable.defaultdrinkimage

    init {
        ingredients["Xtc"] = 100
    }
}