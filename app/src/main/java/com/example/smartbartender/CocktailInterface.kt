package com.example.smartbartender

interface CocktailInterface {
    val name: String
    val ingredients: MutableMap<String, Int>
    val extraIngredients: MutableMap<String, Int>
    val imageResourceId: Int
}