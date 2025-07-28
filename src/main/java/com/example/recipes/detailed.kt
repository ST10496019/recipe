package com.example.recipes

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class detailed : AppCompatActivity() {

    // Lists to receive from MainActivity
    private lateinit var names: ArrayList<String>
    private lateinit var categories: ArrayList<String>
    private lateinit var ratings: ArrayList<Int>
    private lateinit var ingredients: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed) // match your layout file name

        // Link UI elements from layout
        val txtRecipes = findViewById<TextView>(R.id.textRecipes)
        val buttonShowRecipes = findViewById<Button>(R.id.buttonShowRecipes)
        val buttonAvgRating = findViewById<Button>(R.id.buttonAvgRating)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        // Receive data from MainActivity
        names = intent.getStringArrayListExtra("names") ?: arrayListOf()
        categories = intent.getStringArrayListExtra("categories") ?: arrayListOf()
        ratings = intent.getIntegerArrayListExtra("ratings") ?: arrayListOf()
        ingredients = intent.getStringArrayListExtra("ingredients") ?: arrayListOf()

        // Show Recipes
        buttonShowRecipes.setOnClickListener {
            val builder = StringBuilder()
            for (i in names.indices) {
                builder.append("Recipe ${i + 1}:\n")
                builder.append("Name: ${names[i]}\n")
                builder.append("Category: ${categories[i]}\n")
                builder.append("Rating: ${ratings[i]}\n")
                builder.append("Ingredients: ${ingredients[i]}\n\n")
            }

            txtRecipes.text = builder.toString()
        }

        // Show Average Rating
        buttonAvgRating.setOnClickListener {
            if (ratings.isNotEmpty()) {
                val avg = ratings.sum().toDouble() / ratings.size
                Toast.makeText(this, "Average Rating: %.2f".format(avg), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No ratings available", Toast.LENGTH_SHORT).show()
            }
        }

        // Back to MainActivity
        buttonBack.setOnClickListener {
            finish()
        }
    }
}