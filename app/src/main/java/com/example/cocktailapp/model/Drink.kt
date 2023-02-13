package com.example.cocktailapp.model


import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("dateModified")
    val dateModified: String? = null,
    @SerializedName("idDrink")
    val idDrink: String? = null,
    @SerializedName("strAlcoholic")
    val strAlcoholic: String? = null,
    @SerializedName("strCategory")
    val strCategory: String? = null,
    @SerializedName("strDrink")
    val strDrink: String? = null,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String? = null,
    @SerializedName("strGlass")
    val strGlass: String? = null,
    @SerializedName("strImageAttribution")
    val strImageAttribution: String? = null,
    @SerializedName("strImageSource")
    val strImageSource: String? = null,
    @SerializedName("strIngredient1")
    val strIngredient1: String? = null,
    @SerializedName("strIngredient2")
    val strIngredient2: String? = null,
    @SerializedName("strIngredient3")
    val strIngredient3: String? = null,
    @SerializedName("strInstructions")
    val strInstructions: String? = null,
    @SerializedName("strMeasure1")
    val strMeasure1: String? = null,
    @SerializedName("strVideo")
    val strVideo: String? = null
)