package com.example.restaurangmeny

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurangmeny.data.Dish

data class CartItem(val dish: Dish, var quantity: Int)

class MenuViewModel : ViewModel() {

    // This is the real, internal shopping cart. It's mutable and private.
    private val _shoppingCart = MutableLiveData<MutableList<CartItem>>(mutableListOf())

    // This is the public, un-changeable version of the cart that the UI will observe.
    val shoppingCart: LiveData<MutableList<CartItem>> = _shoppingCart

    // This LiveData will hold the calculated total cost.
    private val _totalCost = MutableLiveData(0.0)
    val totalCost: LiveData<Double> = _totalCost

    fun addToCart(dish: Dish, quantity: Int) {
        val cart = _shoppingCart.value ?: mutableListOf()
        val existingItem = cart.find { it.dish.name == dish.name }

        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            cart.add(CartItem(dish, quantity))
        }

        // Update the LiveData to notify observers.
        _shoppingCart.value = cart
        calculateTotalCost()
    }

    private fun calculateTotalCost() {
        var total = 0.0
        _shoppingCart.value?.forEach { cartItem ->
            total += cartItem.dish.price * cartItem.quantity
        }
        _totalCost.value = total
    }
}
