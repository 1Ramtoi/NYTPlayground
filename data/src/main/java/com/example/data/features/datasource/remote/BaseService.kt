package com.example.data.features.datasource.remote

import com.example.data.common.NetworkConstants
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

open class BaseService {

    companion object Client {
        var http: HttpClient = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    isLenient=true
                    prettyPrint=true
                    encodeDefaults=true
                    ignoreUnknownKeys=true
                })
            }
            install(Logging) {
//                level = LogLevel.ALL
                logger= object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }
            defaultRequest {
//                url("https://api.nytimes.com/svc/")
                url {
                    protocol = URLProtocol.HTTPS
                }
                host = "api.nytimes.com/svc"
                parameter("api-key", NetworkConstants.API_KEY)
//                contentType(ContentType.Any) //TODO
                accept(ContentType.Application.Json)
            }
        }.also {
            println("created new client instance")
        }
    }
}