package com.example.composecomponents.ktornetworking

import com.example.composecomponents.ktornetworking.models.KtorClient
import com.example.composecomponents.ktornetworking.models.ResCatItems
import com.example.composecomponents.ktornetworking.models.ResFoodCat
import io.ktor.client.request.get

class HomeRequests {

    suspend fun getFoodList(): List<ResFoodCat> = KtorClient.httpClient.get("https://api.waheguruhub.com/")

    suspend fun getFoodSubList(catName:String): List<ResCatItems> = KtorClient.httpClient.get("https://api.waheguruhub.com/cat/$catName")
}



