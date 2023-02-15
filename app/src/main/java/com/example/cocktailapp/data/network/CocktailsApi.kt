package com.example.cocktailapp.data.network

import com.example.cocktailapp.model.CocktailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CocktailsApi {

    @GET(RPATH)
    suspend fun getAllCocktails(): Response<CocktailModel>

    @GET
    suspend fun getCocktailById(@Url id: String): Response<CocktailModel>

    @GET
    suspend fun getCocktailBySearch(@Url name: String): Response<CocktailModel>

    companion object {
        //www.thecocktaildb.com/api/json/v1/1/search.php?s=

        const val BASE_URL = "http://www.thecocktaildb.com/api/json/v1/1/"
        const val RPATH = "filter.php?a=Alcoholic"
//        const val SPATH = "search.php?s="
//        const val DPATH = "lookup.php?i="

    }
}