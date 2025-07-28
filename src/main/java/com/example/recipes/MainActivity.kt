package com.example.recipes


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    // Parallel lists to store recipe details
    private val recipeNames = mutableListOf<String>()
    private val recipeCategories = mutableListOf<String>()
    private val recipeRatings = mutableListOf<Int>()
    private val recipeIngredients = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAdd = findViewById<Button>(R.id.bottonAdd)
        val buttonView = findViewById<Button>(R.id.buttonView)
        val buttonExit = findViewById<Button>(R.id.buttonExit)


        buttonAdd.setOnClickListener {
            // Inflate the dialog layout
            val inflater = LayoutInflater.from(this)
            val dialogView = inflater.inflate(R.layout.dialog_add_recipe, null)

            // Get references to input fields inside dialog
            val editName = dialogView.findViewById<EditText>(R.id.editName)
            val editCategory = dialogView.findViewById<EditText>(R.id.editCategory)
            val editRating = dialogView.findViewById<EditText>(R.id.editRating)
            val editIngredients = dialogView.findViewById<EditText>(R.id.editIngredients)

            // Create and show the AlertDialog
            AlertDialog.Builder(this)
                .setTitle("Add Recipe")
                .setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    val name = editName.text.toString().trim()
                    val category = editCategory.text.toString().trim()
                    val ratingStr = editRating.text.toString().trim()
                    val ingredients = editIngredients.text.toString().trim()

                    //  Error checking
                    if (name.isEmpty() || category.isEmpty() || ratingStr.isEmpty() || ingredients.isEmpty()) {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    val rating = ratingStr.toIntOrNull()
                    if (rating == null || rating !in 1..5) {
                        Toast.makeText(this, "Rating must be a number between 1 and 5", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    //  Add to lists
                    recipeNames.add(name)
                    recipeCategories.add(category)
                    recipeRatings.add(rating)
                    recipeIngredients.add(ingredients)

                    Toast.makeText(this, "Recipe added!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        // EXIT button
        buttonExit.setOnClickListener {
            finish()
        }


        buttonView.setOnClickListener {
            val intent = Intent(this, detailed::class.java).apply {
                putStringArrayListExtra("names", ArrayList(recipeNames))
                putStringArrayListExtra("categories", ArrayList(recipeCategories))
                putIntegerArrayListExtra("ratings", ArrayList(recipeRatings))
                putStringArrayListExtra("ingredients", ArrayList(recipeIngredients))
            }
            startActivity(intent)
            Toast.makeText(this, "Navigate to detailed view here", Toast.LENGTH_SHORT).show()
        }
    }
}




