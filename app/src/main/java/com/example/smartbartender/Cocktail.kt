package com.example.smartbartender

data class Cocktail(
    override val name:String,
    override val ingredients: MutableMap<String, Int>,
    override val extraIngredients: MutableMap<String, Int>,
    override val imageResourceId: Int
): CocktailInterface
