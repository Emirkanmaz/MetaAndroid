package com.littlelemon.pricecalculator

data class Product(
    val title: String,
    var price: Double,
    var amount: Int
) {

    fun applyDiscount(discountPercent: Int) {
        if (amount > 0 && amount <= 5) {
            price -= (price * discountPercent / 100)
        }
    }

}

//fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
//    return operation(a, b)
//}
//
//fun asd(i: Int, i1: Int): (Int, Int) -> Int {
//    return { x, y -> i + i1 + x + y } // i ve i1 değerlerini kullanarak bir lambda ifadesi döndürür
//}
//
//fun main() {
//    val result = operate(0, 0, asd(1, 2)) // asd(1, 2) bir lambda ifadesi döndürür
//    println(result) // Sonucu yazdırır
//}
