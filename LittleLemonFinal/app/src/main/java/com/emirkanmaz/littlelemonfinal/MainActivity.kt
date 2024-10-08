package com.emirkanmaz.littlelemonfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emirkanmaz.littlelemonfinal.ui.theme.LittleLemonFinalTheme
import com.example.littlelemonfinal.DishDetails
import com.example.littlelemonfinal.Home
import com.example.littlelemonfinal.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonFinalTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Home.route) {
                    composable(Home.route) {
                        HomeScreen(navController)
                    }
                    composable(
                        DishDetails.route + "/{${DishDetails.argDishId}}",
                        arguments = listOf(navArgument(DishDetails.argDishId) { type = NavType.IntType })
                    ) {
                        val id = requireNotNull(it.arguments?.getInt(DishDetails.argDishId)) { "Dish id is null" }
                        DishDetails(id)
                    }
                }
            }
        }
    }
}

