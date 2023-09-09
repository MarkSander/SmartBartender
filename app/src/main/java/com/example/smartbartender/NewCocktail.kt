package com.example.smartbartender

import com.example.smartbartender.CocktailInterface

class NewCocktail (
    override val name: String,
    override val ingredients: MutableMap<String, Int>,
    //override val picture: String,
    override val imageResourceId: Int
) : CocktailInterface

