package com.example.restaurangmeny

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurangmeny.adapter.DishAdapter
import com.example.restaurangmeny.data.DataSource
import com.example.restaurangmeny.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MenuViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dishAdapter = DishAdapter { dish, quantity ->
            viewModel.addToCart(dish, quantity)
            val toastMessage = getString(R.string.added_to_cart_format, quantity, dish.name)
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }

        binding.dishRecyclerView.adapter = dishAdapter

        viewModel.totalCost.observe(this) { newTotal ->
            binding.totalCostTextview.text = getString(R.string.price_format, newTotal)
        }

        binding.appetizersButton.setOnClickListener {
            dishAdapter.submitList(DataSource.appetizers)
        }

        binding.mainCoursesButton.setOnClickListener {
            dishAdapter.submitList(DataSource.mainCourses)
        }

        binding.dessertsButton.setOnClickListener {
            dishAdapter.submitList(DataSource.desserts)
        }

        // Show appetizers by default
        dishAdapter.submitList(DataSource.appetizers)
    }
}
