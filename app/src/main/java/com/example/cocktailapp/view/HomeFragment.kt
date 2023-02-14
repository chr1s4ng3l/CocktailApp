package com.example.cocktailapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.R
import com.example.cocktailapp.adapter.CocktailAdapter
import com.example.cocktailapp.databinding.FragmentViewBinding
import com.example.cocktailapp.model.CocktailModel
import com.example.cocktailapp.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val binding: FragmentViewBinding by lazy {
        FragmentViewBinding.inflate(layoutInflater)
    }

    private val cocktailAdapter by lazy {
        CocktailAdapter {

            cocktailViewModel.title = it.strDrink.toString()
            cocktailViewModel.image = it.strDrinkThumb.toString()
            cocktailViewModel.ingredient1 = it.strIngredient1.toString()
            cocktailViewModel.ingredient2 = it.strIngredient2.toString()
            cocktailViewModel.ingredient3 = it.strIngredient3.toString()
            cocktailViewModel.instructions1 = it.strInstructions.toString()
            cocktailViewModel.title = it.strDrink.toString()

          findNavController().navigate(R.id.action_home_to_details)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.recyclerCocktails.apply {
            //RecyclerView
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = cocktailAdapter

        }

        getSongsClassic()

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun getSongsClassic() {
        //ViewModel here
        cocktailViewModel.drink.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                }
                is UIState.SUCCESS<CocktailModel> -> {
                    Log.d(TAG, "onCreateView: ${state.response}")
                    cocktailAdapter.updateItems(
                        state.response.drinks ?: emptyList()
                    )
                }
                is UIState.ERROR -> {
                    state.error.localizedMessage?.let {
                        showError(it) {

                        }
                    }
                }
            }
        }
    }

}