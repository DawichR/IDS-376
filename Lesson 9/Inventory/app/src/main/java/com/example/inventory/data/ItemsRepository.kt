package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repositorio con los metodos a utilizar
 */
interface ItemsRepository {
  /**
 *Obtenemos una lista de todos los articulos 
 */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Devuelve un articulo dependiendo de su ID
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Insertar un articulo en la base de datos
     */
    suspend fun insertItem(item: Item)

    /**
     * Eliminar un articulo en la base de datos
     */
    suspend fun deleteItem(item: Item)

    /**
     * Actualizar un articulo en la base de datos
     */
    suspend fun updateItem(item: Item)
}
