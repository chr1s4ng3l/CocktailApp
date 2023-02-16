package com.example.cocktailapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.FragmentViewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : Fragment() {


    private val binding: FragmentViewBinding by lazy {
        FragmentViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //Hide searchView
        binding.searchView.isGone = true






        // Inflate the layout for this fragment
        return binding.root
    }

}