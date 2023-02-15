package com.example.cocktailapp.data.network

import android.util.Log
import com.example.cocktailapp.model.CocktailModel
import com.example.cocktailapp.utils.FailResponse
import com.example.cocktailapp.utils.NullResponse
import com.example.cocktailapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "CocktailsRepository"

interface CocktailRepository {
    fun getListCocktails(): Flow<UIState<CocktailModel>>
    fun getListCocktailsById(id: String): Flow<UIState<CocktailModel>>
    fun getListCocktailsByName(query: String): Flow<UIState<CocktailModel>>

}

class CocktailRepositoryImp @Inject constructor(private val api: CocktailsApi) :
    CocktailRepository {


    override fun getListCocktails(): Flow<UIState<CocktailModel>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getAllCocktails()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getListCocktails: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullResponse()
            } else
                throw FailResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getListCocktails: $e")
            emit(UIState.ERROR(e))
        }

    }

    override fun getListCocktailsById(id: String): Flow<UIState<CocktailModel>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getCocktailById("lookup.php?i=${id}")
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getListCocktailsID: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullResponse()
            } else
                throw FailResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getListCocktails: $e")
            emit(UIState.ERROR(e))
        }

    }

    override fun getListCocktailsByName(query: String): Flow<UIState<CocktailModel>> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getCocktailBySearch("search.php?s=${query}")
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getListCocktails: $it")
                    emit(UIState.SUCCESS(it))
                } ?: throw NullResponse()
            } else
                throw FailResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            Log.e(TAG, "getListCocktails: $e")
            emit(UIState.ERROR(e))
        }

    }
}