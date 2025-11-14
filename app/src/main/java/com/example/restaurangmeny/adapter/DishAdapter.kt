package com.example.restaurangmeny.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurangmeny.R
import com.example.restaurangmeny.data.Dish
import com.example.restaurangmeny.databinding.DishListItemBinding

class DishAdapter(
    private val onAddToCartClicked: (Dish, Int) -> Unit
) : ListAdapter<Dish, DishAdapter.DishViewHolder>(DishDiffCallback) {

    class DishViewHolder(private val binding: DishListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: Dish, onAddToCartClicked: (Dish, Int) -> Unit) {
            var currentQuantity = 1
            val context = binding.root.context

            binding.dishName.text = dish.name
            binding.dishDescription.text = dish.description
            binding.dishPrice.text = context.getString(R.string.price_format, dish.price)
            binding.dishImage.setImageResource(dish.image)
            binding.quantityText.text = currentQuantity.toString()

            // Treat the TextViews as the buttons
            val minusButton: TextView = binding.minusButton
            val plusButton: TextView = binding.plusButton

            minusButton.setOnClickListener {
                if (currentQuantity > 1) {
                    currentQuantity--
                    binding.quantityText.text = currentQuantity.toString()
                }
            }

            plusButton.setOnClickListener {
                currentQuantity++
                binding.quantityText.text = currentQuantity.toString()
            }

            binding.addToCartButton.setOnClickListener {
                onAddToCartClicked(dish, currentQuantity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = DishListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(getItem(position), onAddToCartClicked)
    }

    object DishDiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }
}