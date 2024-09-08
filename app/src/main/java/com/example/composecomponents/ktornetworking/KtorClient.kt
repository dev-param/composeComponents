package com.example.composecomponents.ktornetworking.models

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

object KtorClient {
    private val  json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient =true
        prettyPrint =true
    }

    val httpClient: HttpClient = HttpClient(Android){
        install(HttpTimeout){
            socketTimeoutMillis = 30000
            connectTimeoutMillis = 30000
            requestTimeoutMillis = 30000
        }
        install(Logging){
            logger = object: Logger {
                override fun log(message: String) {
                    Log.d("ktor", message)
                }
            }



        }
        install(JsonFeature){
            serializer = KotlinxSerializer(json)
        }

        install(ResponseObserver){
            onResponse {
                Log.d("res", "${it.status.value }}")
            }
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

        }
    }


}