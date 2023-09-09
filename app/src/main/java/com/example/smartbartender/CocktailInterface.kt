package com.example.smartbartender

interface CocktailInterface {
    val name: String // naam van de cocktail
    //val ingredients: List<String> //ingredienten van de cocktail
    //val ingredientAmount: List<Int> //hoeveelheid in ml
    val ingredients: MutableMap<String, Int>
    //val picture: String //plaatje van de cocktail
    val imageResourceId: Int
}