package com.example.restaurangmeny

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurangmeny.adapter.DishAdapter
import com.example.restaurangmeny.data.DataSource
import com.example.restaurangmeny.data.Dish

data class CartItem(val dish: Dish, var quantity: Int)

class MainActivity : AppCompatActivity() {

    private val shoppingCart = mutableListOf<CartItem>()
    private lateinit var totalCostTextView: TextView
    private lateinit var dishAdapter: DishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        totalCostTextView = findViewById(R.id.total_cost_textview)
        val dishRecyclerView = findViewById<RecyclerView>(R.id.dish_recycler_view)

        dishAdapter = DishAdapter { dish, quantity ->
            addToCart(dish, quantity)
        }
        dishRecyclerView.adapter = dishAdapter

        val appetizersButton = findViewById<Button>(R.id.appetizers_button)
        val mainCoursesButton = findViewById<Button>(R.id.main_courses_button)
        val dessertsButton = findViewById<Button>(R.id.desserts_button)

        appetizersButton.setOnClickListener {
            dishAdapter.submitList(DataSource.appetizers)
        }

        mainCoursesButton.setOnClickListener {
            dishAdapter.submitList(DataSource.mainCourses)
        }

        dessertsButton.setOnClickListener {
            dishAdapter.submitList(DataSource.desserts)
        }

        // Show appetizers by default
        dishAdapter.submitList(DataSource.appetizers)
    }

    private fun addToCart(dish: Dish, quantity: Int) {
        val existingCartItem = shoppingCart.find { it.dish.name == dish.name }

        if (existingCartItem != null) {
            existingCartItem.quantity += quantity
        } else {
            shoppingCart.add(CartItem(dish, quantity))
        }
        updateTotalCost()
        Toast.makeText(this, "$quantity ${dish.name} har lagts till", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalCost() {
        var total = 0.0
        shoppingCart.forEach { total += it.dish.price * it.quantity }
        totalCostTextView.text = "$total kr"
    }
}
