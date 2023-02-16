package com.example.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.data.network.CocktailRepositoryImp
import com.example.cocktailapp.model.CocktailModel
import com.example.cocktailapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailsViewModel @Inject constructor(private val coRepo: CocktailRepositoryImp) :
    ViewModel() {

    var id = ""
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _drink: MutableLiveData<UIState<CocktailModel>> = MutableLiveData(UIState.LOADING)
    val drink: MutableLiveData<UIState<CocktailModel>> get() = _drink

    private val _drinkId: MutableLiveData<UIState<CocktailModel>> = MutableLiveData(UIState.LOADING)
    val drinkId: MutableLiveData<UIState<CocktailModel>> get() = _drinkId

    private val _drinkByName: MutableLiveData<UIState<CocktailModel>> = MutableLiveData(UIState.LOADING)
    val drinkByName: MutableLiveData<UIState<CocktailModel>> get() = _drinkByName
    var isLoading = MutableLiveData<Boolean>()


    init {
        getAllDrinks()
        getAllDrinksById()
        getAllDrinksBySearch()
    }


    private fun getAllDrinks() {
        isLoading.postValue(true)
        viewModelScope.launch(ioDispatcher) {
            coRepo.getListCocktails().collect() {
                _drink.postValue(it)

            }
        }
    }


    fun getAllDrinksById(id: String? = null) {
        id?.let { id ->
            viewModelScope.launch(ioDispatcher) {
                coRepo.getListCocktailsById(id).collect() {
                    _drinkId.postValue(it)

                }
            }
        }

    }

     fun getAllDrinksBySearch(query: String? = null) {
        query?.let {
            viewModelScope.launch(ioDispatcher) {
                coRepo.getListCocktailsByName(query).collect() {
                    _drinkByName.postValue(it)

                }
            }

        }

    }


}