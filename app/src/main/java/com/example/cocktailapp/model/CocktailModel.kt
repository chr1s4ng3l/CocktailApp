package com.example.cocktailapp.model


import com.google.gson.annotations.SerializedName

data class CocktailModel(
    @SerializedName("drinks")
    val drinks: List<Drink>? = null
)