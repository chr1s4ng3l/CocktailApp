package com.example.cocktailapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.databinding.CocktailItemBinding
import com.example.cocktailapp.model.Drink
import com.squareup.picasso.Picasso

class CocktailAdapter(
    private val itemSet: MutableList<Drink> = mutableListOf(),
    private val onItemClick: (Drink) -> Unit
) : RecyclerView.Adapter<CocktailViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<Drink>, context: Context) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder =
        CocktailViewHolder(
            CocktailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)
}


class CocktailViewHolder(private val binding: CocktailItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    //Binding the view in the cardView
    fun bind(item: Drink, onItemClick: (Drink) -> Unit) {


        binding.tvCocktailName.text = item.strDrink
        binding.tvIngredient.text = item.strIngredient1
        Picasso.get().load(item.strDrinkThumb).into(binding.imageCocktail)

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }

}