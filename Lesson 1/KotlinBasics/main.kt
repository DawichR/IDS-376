import kotlin.io.println

fun main() {

    println("Operaciones Aritmeticas")
    println("Suma: " + 1 + 1)
    println("Resta: " + (53 - 3))
    println("Division: " + (50 / 10))
    println("Division: " + (1.0 / 2.0))
    println("Multiplicacion: " + (2.0 * 3.5))
    println("")

    println("Operaciones Aritmeticas (diferentes combinaciones)")
    println("Multiplicacion: " + (6 * 50))
    println("Multiplicacion: " + (6.0 * 50.0))
    println("Multiplicacion: " + (6.0 * 50))
    println("")

    println("Operaciones Aritmeticas (Utilizando palabras reservadas")
    println("Multiplicacion: " + 2.times(5))
    println("Multiplicacion: " + 5.4.plus(6))
    println("Multiplicacion: " + 24.div(3))
    println("")

    println("Uso de variables")
    val a: Int = 5
    val b = a.toByte()
    val b2: Byte = 1
    val i5: String = b2.toString()
    val i6: Double = b2.toDouble()
    println(b)
    println(b2)
    println(i5)
    println(i6)
    println("")

    println("Uso de cadenas y caracteres ")
    val numeroPeces = 2
    val numeroPlantas = 10
    println("Actualmente el numero de peces son: $numeroPeces y de plantas: $numeroPlantas ")
    println("Ambos suman un total de: ${numeroPeces + numeroPlantas} ")

    println("Uso de booleanos y condicionales ")
    val bueno = true
    val totales = 15

    if (bueno) println("Soy bueno") else println("Soy Malo")

    if (totales in 1..100) println("Se encuentra dentro de los totales")

    // Esto viene simulado un SWITCH
    when (totales) {
        0 -> println("No hay totales")
        in 1..15 -> println("Esta en el rango de los 15 totales!")
        else -> println("Mas de 15 totales!")
    }
    println("")

    println("Uso de nulos (Nullability) ")
    var numeroNulo: Int? = null // variable entera qeu puede ser numerica
    numeroNulo = 5

    if (numeroNulo != null) {
        numeroNulo = numeroNulo.dec() // su numeroNulo no esta nulo, decrementa
    }
    println(numeroNulo)
    println("")

    println("Arreglos, listas e iteracciones ")

	println("Lista estatica")
    val juegos = listOf("PUBG", "COD", "Fornite")
    println("Lista de juegos: " + juegos)
    println("")

    println("Lista mutable")
    val peces = mutableListOf("tuna", "salmon", "tiburon")
    peces.remove("tiburon")
    println("Lista de peces: " + peces)
    println("")

    val listaPrueba = arrayOf("Hola", "Adios", "Buenas")
    println(java.util.Arrays.toString(listaPrueba))

	println("Otra manera de imprimir la lista")
    for (element in listaPrueba) {
        print(element + " ")
    }
    println("")

	println("Arrray dinamico (con tamaño de 5)")
	val arregloDinamico = Array (5) { it * 2 }
	println(java.util.Arrays.toString(arregloDinamico))
	println("Cantidad de elementos: " + arregloDinamico.count())
    println("")

    println("Lista de varios tipos")
	val mumeros = intArrayOf(1, 2, 5)
	val oceanos = listOf("Atlantico", "Pacifico")
	val ListaNoTipada = listOf(mumeros, oceanos, "Avena")
	println(ListaNoTipada)
    println("")

	println("For Loops directos")
    for (i in 1..5) print(i) 
    println("")
    for (i in 5 downTo 1) print(i)
    println("")
    for (i in 3..40 step 2) print(" " + i)
    println("")
    for (i in 'a'..'e') print(i)
    println("\n\n")

	println("While/ Do While")
    var burbujas = 0
    while (burbujas < 50) {
        burbujas++
    }
    println("$burbujas burbujas en el agua\n")

    do {
        burbujas--
    } while (burbujas > 50)
    println("$burbujas burbujas en el agua\n")
    println("")
	
	println("Funcion de Kotlin para repetir algo de manera parametrizable")
    repeat(2) { println("Repetir") }
}
