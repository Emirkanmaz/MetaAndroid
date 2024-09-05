package com.littlelemon.pricecalculator

class PriceCalculator() {

    fun calculatePrice(item1Price: Double, item2Price: Double, taxrate: Double): Double {
        return (item1Price + item2Price) * (1.0 + taxrate)
    }
}