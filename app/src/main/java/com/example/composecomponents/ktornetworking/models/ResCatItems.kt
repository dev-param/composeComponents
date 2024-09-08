package com.example.composecomponents.ktornetworking.models

import kotlinx.serialization.Serializable

@Serializable
data class ResCatItems(
    val cats: List<String>,
    val detail: String,
    val id: Int,
    val images: List<String>,
    val name: String,
    val price: Price
)