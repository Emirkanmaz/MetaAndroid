package com.littlelemon.pricecalculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PriceCalculatorTest {

    private val classUnderTest = PriceCalculator()

    @Test
    fun `validate calculation`() {
        val result = classUnderTest.calculatePrice(item1Price = 3.00, item2Price = 5.00, taxrate = 0.2)
        assertEquals(9.62, result ,0.1)
    }


}