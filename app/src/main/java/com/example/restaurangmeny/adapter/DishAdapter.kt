package com.example.restaurangmeny.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurangmeny.R
import com.example.restaurangmeny.data.Dish

class DishAdapter(
    private val onAddToCartClicked: (Dish, Int) -> Unit
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    private var dishList: List<Dish> = emptyList()

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishImage: ImageView = view.findViewById(R.id.dish_image)
        val dishName: TextView = view.findViewById(R.id.dish_name)
        val dishDescription: TextView = view.findViewById(R.id.dish_description)
        val dishPrice: TextView = view.findViewById(R.id.dish_price)
        val minusButton: Button = view.findViewById(R.id.minus_button)
        val quantityText: TextView = view.findViewById(R.id.quantity_text)
        val plusButton: Button = view.findViewById(R.id.plus_button)
        val addToCartButton: Button = view.findViewById(R.id.add_to_cart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dish_list_item, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishList[position]
        var currentQuantity = 1

        holder.dishName.text = dish.name
        holder.dishDescription.text = dish.description
        holder.dishPrice.text = "${dish.price} kr"
        holder.dishImage.setImageResource(dish.image)
        holder.quantityText.text = currentQuantity.toString()

        holder.minusButton.setOnClickListener {
            if (currentQuantity > 1) {
                currentQuantity--
                holder.quantityText.text = currentQuantity.toString()
            }
        }

        holder.plusButton.setOnClickListener {
            currentQuantity++
            holder.quantityText.text = currentQuantity.toString()
        }

        holder.addToCartButton.setOnClickListener {
            onAddToCartClicked(dish, currentQuantity)
        }
    }

    override fun getItemCount() = dishList.size

    fun submitList(dishes: List<Dish>) {
        dishList = dishes
        notifyDataSetChanged()
    }
}