package com.example.basicnavigation

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "Home"
}

object Menu : Destinations {
    const val argOrderNo = "OrderNo"
    override val route = "Menu"
}