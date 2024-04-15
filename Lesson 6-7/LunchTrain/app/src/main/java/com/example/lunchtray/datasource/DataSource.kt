package com.example.lunchtray.datasource

import com.example.lunchtray.model.MenuItem.AccompanimentItem
import com.example.lunchtray.model.MenuItem.EntreeItem
import com.example.lunchtray.model.MenuItem.SideDishItem

object DataSource {

val entreeMenuItems = listOf(
    EntreeItem(
        name = "Coliflor Frita",
        description = "Coliflor entera, marinada, asada y frita",
        price = 8.50,
    ),
    EntreeItem(
        name = "Chili de Tres Frijoles",
        description = "Frijoles negros, frijoles rojos, frijoles rojos, cocidos a fuego lento, cubiertos con cebolla",
        price = 3.50,
    ),
    EntreeItem(
        name = "Pasta de Champiñones",
        description = "Pasta penne, champiñones, albahaca, con tomates ciruela cocidos en ajo y aceite de oliva",
        price = 6.00,
    ),
    EntreeItem(
        name = "Sartén de Frijoles Negros Picantes",
        description = "Vegetales de temporada, frijoles negros, mezcla de especias de la casa, servidos con aguacate y cebollas en vinagre rápido",
        price = 7.00,
    )
)

val sideDishMenuItems = listOf(
    SideDishItem(
        name = "Ensalada de Verano",
        description = "Tomates de herencia, lechuga mantecosa, duraznos, aguacate, aderezo balsámico",
        price = 2.00,
    ),
    SideDishItem(
        name = "Sopa de Calabaza",
        description = "Calabaza asada, pimientos asados, aceite de chili",
        price = 3.50,
    ),
    SideDishItem(
        name = "Papas Picantes",
        description = "Papas mármol, asadas y fritas en mezcla de especias de la casa",
        price = 2.50,
    ),
    SideDishItem(
        name = "Arroz de Coco",
        description = "Arroz, leche de coco, lima y azúcar",
        price = 1.00,
    )
)

val accompanimentMenuItems = listOf(
    AccompanimentItem(
        name = "Panecillo de Almuerzo",
        description = "Panecillo recién horneado hecho en casa",
        price = 0.75,
    ),
    AccompanimentItem(
        name = "Frutos Rojos Mixtos",
        description = "Fresas, arándanos, frambuesas y moras",
        price = 1.25,
    ),
    AccompanimentItem(
        name = "Verduras en Escabeche",
        description = "Pepinos y zanahorias en escabeche, hechos en casa",
        price = 0.75,
    )
)
}
