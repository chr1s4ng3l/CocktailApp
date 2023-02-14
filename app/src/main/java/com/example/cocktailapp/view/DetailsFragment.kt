package com.example.cocktailapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


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


        binding.textViewTitle.text = cocktailViewModel.title
        binding.tvIngredient.text =
            "* ${cocktailViewModel.ingredient1}\n * ${cocktailViewModel.ingredient2}"
        binding.tvInstructions.text = cocktailViewModel.instructions1
        Picasso.get().load(cocktailViewModel.image).into(binding.imageViewDetails)


        // Inflate the layout for this fragment
        return binding.root
    }

}