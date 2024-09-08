package com.example.composecomponents.ktornetworking.models

import kotlinx.serialization.Serializable


@Serializable
data class Price(
    val `500ml`: String? = null,
    val a: Int? = null,
    val item_type: List<String>,
    val pickup: Int? = null,
    val service: Int? = null,
    val l: Int? = null,
    val m: Int? = null,
    val s: Int? = null,
)