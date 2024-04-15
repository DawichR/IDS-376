package com.example.inventory.data

import android.content.Context

/**
 * App container para la inyeccion de dependencia
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}


class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementando repositorio
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
