package com.littlelemon.littlelemoningredients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class IngredientsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_ingredients)

            findViewById<TextView>(R.id.ingredients_list).text =
                when (intent.getStringExtra(EXTRA_DISH_NAME_KEY)) {
                    "Hamburger" -> "Minced meat\nBun\nTomato"
                    "Pasta" -> "Spaghetti\nTomato\nParmesan"
                    else -> "Unknown dish"
                }

    }

    companion object {
        private const val EXTRA_DISH_NAME_KEY = "DishName"

        fun start(context: Context, dishName: String) {
            var intent = Intent(context, IngredientsActivity::class.java).apply {
                putExtra(EXTRA_DISH_NAME_KEY, dishName)
            }
            context.startActivity(intent)

        }
    }
}
