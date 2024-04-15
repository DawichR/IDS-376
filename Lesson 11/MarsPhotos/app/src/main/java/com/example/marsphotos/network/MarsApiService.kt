package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    /**
     * Obtiene el listado de fotos mapeados en la clase MarsPhoto
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
