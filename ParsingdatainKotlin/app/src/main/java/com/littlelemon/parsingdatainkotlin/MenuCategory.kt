package com.littlelemon.parsingdatainkotlin

import kotlinx.serialization.Serializable

@Serializable
class MenuCategory(
    val menu: List<String>
)