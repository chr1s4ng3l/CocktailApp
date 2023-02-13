package com.example.cocktailapp.data.network

import com.example.cocktailapp.model.CocktailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {

    @GET(PATH)
    suspend fun getAllCocktails(): Response<CocktailModel>

    companion object {
        //www.thecocktaildb.com/api/json/v1/1/search.php?s=

        const val BASE_URL = "www.thecocktaildb.com/api/json/v1/1/"
        const val PATH = "search.php?s="

    }
}