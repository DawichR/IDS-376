package com.example.amphibians.data

import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.AmphibiansApiService

/**
 * El repositorio recupera datos de anfibios de una fuente de datos subyacente.
 */
interface AmphibiansRepository {
    /** Recupera la lista de anfibios de la fuente de datos subyacente */
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Implementación de red del repositorio que recupera datos de anfibios de una fuente de datos subyacente.
 */
class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    /** Recupera la lista de anfibios de la fuente de datos subyacente */
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}
