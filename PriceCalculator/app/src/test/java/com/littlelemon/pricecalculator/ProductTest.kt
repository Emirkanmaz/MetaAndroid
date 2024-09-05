package com.littlelemon.pricecalculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ProductTest{

    @Test
    fun `first test`(){
        val product = Product("Spaghetti", 20.00, 3)
        product.applyDiscount(20)
        assertEquals(16.0, product.price)
    }

    @Test
    fun `second test`(){
        val product = Product("Steak", 30.00, 8)
        product.applyDiscount(20)
        assertEquals(30.0, product.price)
    }

    @Test
    fun `third test`(){
        val product = Product("Lasagna", 10.0, 0)
        product.applyDiscount(20)
        assertEquals(10.0, product.price)
    }
}