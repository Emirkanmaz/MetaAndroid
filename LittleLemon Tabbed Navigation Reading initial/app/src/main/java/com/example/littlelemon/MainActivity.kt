package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MyBottomNavigation(navcontroller = navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)){
            NavHost(navController = navController, startDestination = Home.route) {
                composable(Menu.route){ MenuScreen()}
                composable(Home.route){ HomeScreen()}
                composable(Location.route){ LocationScreen()}
            }


        }
    }
}

@Composable
fun MyBottomNavigation(navcontroller: NavController) {
    val listOfDestinations = listOf(Menu, Home, Location)
    val selectedIndex = rememberSaveable {
        mutableStateOf(1)
    }
    BottomNavigation {
        listOfDestinations.forEachIndexed { index, destination ->
            BottomNavigationItem(
                label = { Text(destination.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon),
                        contentDescription = destination.title
                    )
                },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navcontroller.navigate(listOfDestinations[index].route){
                        popUpTo(Home.route)
                        launchSingleTop = true
                    }
                })
        }
    }
}