package com.example.cocktailapp.view

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.R
import com.example.cocktailapp.adapter.CocktailAdapter
import com.example.cocktailapp.databinding.FragmentViewBinding
import com.example.cocktailapp.model.CocktailModel
import com.example.cocktailapp.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view.*


private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : BaseFragment(), SearchView.OnQueryTextListener {

    private val binding: FragmentViewBinding by lazy {
        FragmentViewBinding.inflate(layoutInflater)
    }

    private val cocktailAdapter by lazy {
        CocktailAdapter {

            cocktailViewModel.id = it.idDrink.toString()
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

        binding.searchView.setOnQueryTextListener(this)

        //Swipe Refresh
        binding.swipeRefresh.setOnRefreshListener {
            cocktailViewModel.isLoading.postValue(false)
            getCocktailsRandom()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.recyclerCocktails.apply {

            //RecyclerView
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = cocktailAdapter

        }

        getCocktailsRandom()
        getCocktailsByName()

        cocktailViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getCocktailsRandom() {
        //ViewModel here
        cocktailViewModel.drink.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    cocktailViewModel.isLoading.postValue(true)
                }
                is UIState.SUCCESS<CocktailModel> -> {
                    cocktailViewModel.isLoading.postValue(false)
                    Log.d(TAG, "onCreateView: ${state.response}")
                    cocktailAdapter.updateItems(
                        state.response.drinks ?: emptyList()
                    )
                }
                is UIState.ERROR -> {
                    cocktailViewModel.isLoading.postValue(false)
                    state.error.localizedMessage?.let {
                        showError(it) {


                        }
                    }
                }
            }
        }

    }


    private fun getCocktailsByName() {
        //ViewModel here
        cocktailViewModel.drinkByName.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    cocktailViewModel.isLoading.postValue(true)
                }
                is UIState.SUCCESS<CocktailModel> -> {
                    cocktailViewModel.isLoading.postValue(false)
                    Log.d(TAG, "onCreateView: ${state.response}")
                    cocktailAdapter.updateItems(
                        state.response.drinks ?: emptyList()
                    )
                }
                is UIState.ERROR -> {
                    cocktailViewModel.isLoading.postValue(false)
                    state.error.localizedMessage?.let {
                        showError(it) {


                        }
                    }
                }
            }
        }

    }


    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0)
    }

    private fun searchByName(query: String) {
        cocktailViewModel.getAllDrinksBySearch(query)
        hideKeyboard()

    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query)
            Toast.makeText(requireContext(), "$query", Toast.LENGTH_LONG).show()
        }

        return true
    }



    override fun onQueryTextChange(newText: String?): Boolean {


        return true
    }


}