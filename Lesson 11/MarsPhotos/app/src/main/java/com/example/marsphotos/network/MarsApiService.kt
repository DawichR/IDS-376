package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

    // Api base para consumir las fotos de marte
    private const val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    /**
    * Constructor de Retrofit para construir un objeto Retrofit utilizando un convertidor kotlinx.serialization
    */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Objeto de servicio Retrofit para crear llamadas de API
     */
    interface MarsApiService {
        @GET("photos")
        suspend fun getPhotos(): List<MarsPhoto>
    }

    /**
     * Un objeto Api público que expone el servicio Retrofit inicializado de forma diferida
     */
    object MarsApi {
        val retrofitService: MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
}
