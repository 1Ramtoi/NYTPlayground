package com.example.data.features.datasource.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

open class BaseService {
    val HOST = "https://api.nytimes.com/svc/"

    companion object Client {
        var http: HttpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    encodeDefaults=true
                    ignoreUnknownKeys=true
                })
            }
            install(Logging) {
                logger= object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            defaultRequest {
                url("https://api.nytimes.com/svc/")
                contentType(ContentType.Any) //TODO
                accept(ContentType.Any)
            }
        }

    }
}