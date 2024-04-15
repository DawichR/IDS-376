
package com.example.inventory.ui.navigation

/**
 * Interface para navergar entre las Apps
 */
interface NavigationDestination {
    /**
     * una ruta unica
     */
    val route: String

    /**
     * Entero que represente dicha ruta o recurso
     */
    val titleRes: Int
}
