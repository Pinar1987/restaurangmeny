package com.example.restaurangmeny

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Dish(val name: String, val description: String, val image: Int)

class MainActivity : AppCompatActivity() {

    private val appetizers = listOf(
        Dish("Toast Skagen", "Klassisk Toast Skagen med räkor, majonnäs, dill och löjrom.", R.drawable.appetizers1),
        Dish("Carpaccio", "Lövtunn oxfilé med olivolja, pinjenötter, ruccola och parmesan.", R.drawable.appetizers2),
        Dish("Bruschetta", "Grillat bröd med tomat, vitlök, basilika och olivolja.", R.drawable.appetizers3)
    )

    private val mainCourses = listOf(
        Dish("Biff Rydberg", "Tärnad oxfilé med lök, potatis och äggula.", R.drawable.maindishes1),
        Dish("Pestopasta", "Pasta med hemgjord pesto, soltorkade tomater och parmesan.", R.drawable.maindishes2),
        Dish("Halstrad lax", "Lax med potatispuré, vitvinssås och sparris.", R.drawable.maindishes3)
    )

    private val desserts = listOf(
        Dish("Crème brûlée", "Klassisk fransk efterrätt med karamelliserat socker.", R.drawable.dessert1),
        Dish("Chokladfondant", "Varm chokladkaka med rinnig mitt, serveras med vaniljglass.", R.drawable.dessert2),
        Dish("Pannacotta", "Italiensk gräddpudding med smak av vanilj och toppad med bärsås.", R.drawable.dessert3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val appetizersButton = findViewById<Button>(R.id.appetizers_button)
        val mainCoursesButton = findViewById<Button>(R.id.main_courses_button)
        val dessertsButton = findViewById<Button>(R.id.desserts_button)

        appetizersButton.setOnClickListener {
            updateMenu(appetizers)
        }

        mainCoursesButton.setOnClickListener {
            updateMenu(mainCourses)
        }

        dessertsButton.setOnClickListener {
            updateMenu(desserts)
        }


        updateMenu(appetizers)
    }

    private fun updateMenu(dishes: List<Dish>) {
        val dish1Name = findViewById<TextView>(R.id.dish_1_name)
        val dish1Description = findViewById<TextView>(R.id.dish_1_description)
        val dish1Image = findViewById<ImageView>(R.id.appetizer_1_image)

        val dish2Name = findViewById<TextView>(R.id.dish_2_name)
        val dish2Description = findViewById<TextView>(R.id.dish_2_description)
        val dish2Image = findViewById<ImageView>(R.id.appetizer_2_image)

        val dish3Name = findViewById<TextView>(R.id.dish_3_name)
        val dish3Description = findViewById<TextView>(R.id.dish_3_description)
        val dish3Image = findViewById<ImageView>(R.id.appetizer_3_image)

        dish1Name.text = dishes[0].name
        dish1Description.text = dishes[0].description
        dish1Image.setImageResource(dishes[0].image)

        dish2Name.text = dishes[1].name
        dish2Description.text = dishes[1].description
        dish2Image.setImageResource(dishes[1].image)

        dish3Name.text = dishes[2].name
        dish3Description.text = dishes[2].description
        dish3Image.setImageResource(dishes[2].image)
    }
}
