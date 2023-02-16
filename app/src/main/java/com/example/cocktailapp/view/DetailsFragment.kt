package com.example.cocktailapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.FragmentDetailsBinding
import com.example.cocktailapp.model.CocktailModel
import com.example.cocktailapp.model.Drink
import com.example.cocktailapp.utils.UIState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "Hola"
@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private val binding: FragmentDetailsBinding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.circleImageBack.setOnClickListener{
            activity?.onBackPressed()
        }

        cocktailViewModel.getAllDrinksById(cocktailViewModel.id)

        cocktailViewModel.drinkId.observe(viewLifecycleOwner){
            when (it) {
                is UIState.LOADING -> {
                }
                is UIState.SUCCESS<CocktailModel> -> {
                    Log.d(TAG, "onCreateView AAA: ${it.response.drinks}")
                    val list = it.response.drinks
                    list?.forEach { drink ->
                        binding.tvIngredient.text = "* ${drink.strIngredient1}\n * ${drink.strIngredient2}"
                        binding.tvInstructions.text = drink.strInstructions.toString()
                        binding.textViewTitle.text = drink.strDrink.toString()
                        Picasso.get().load(drink.strDrinkThumb).into(binding.imageViewDetails)

                    }

                }
                is UIState.ERROR -> {
                    it.error.localizedMessage?.let {
                        showError(it) {


                        }
                    }
                }
            }

        }






        // Inflate the layout for this fragment
        return binding.root
    }

}