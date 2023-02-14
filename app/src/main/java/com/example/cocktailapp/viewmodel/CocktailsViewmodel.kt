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


    var instructions = ""
    var ingredints = ""
    var image = ""
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _drink: MutableLiveData<UIState<CocktailModel>> = MutableLiveData(UIState.LOADING)
    val drink: MutableLiveData<UIState<CocktailModel>> get() = _drink



    init {
        getAllDrinks()
    }


    private fun getAllDrinks() {
                viewModelScope.launch(ioDispatcher) {
                    coRepo.getListCocktails().collect() {
                        _drink.postValue(it)

                        }
                    }
                }



}