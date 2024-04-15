
package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService

/**
 *Repositorio para poder obtener fotos de marsApi
 */
interface MarsPhotosRepository {
    /** funcion para obtener las MarsPhoto de marsApi */
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

/**
 * Repositorio para obtener fotos utilizando un servicio
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    /** Obtener listado MarsPhoto des marsApi (Llamado utilizando un servicio)*/
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}
