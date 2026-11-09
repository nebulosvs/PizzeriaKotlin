package servicio

import modelo.Pedido
import modelo.Producto

class InventarioService(
    private val inventario: MutableMap<String, Int>
) {

    init {
        require(inventario.values.all { it >= 0 }) {
            "El inventario no puede contener cantidades negativas."
        }
    }

    private fun calcularIngredientesNecesarios(
        productos: List<Producto>,
        cantidades: List<Int>
    ): Map<String, Int> {

        require(productos.isNotEmpty()) {
            "Debe existir al menos un producto."
        }

        require(productos.size == cantidades.size) {
            "Cada producto debe tener una cantidad asociada."
        }

        require(cantidades.all { it > 0 }) {
            "Las cantidades deben ser positivas."
        }

        val ingredientesNecesarios = mutableMapOf<String, Int>()

        for (indice in productos.indices) {
            val producto = productos[indice]
            val cantidad = cantidades[indice]

            for ((ingrediente, cantidadPorUnidad) in producto.ingredientes) {
                val cantidadNecesaria =
                    Math.multiplyExact(cantidadPorUnidad, cantidad)

                ingredientesNecesarios[ingrediente] = Math.addExact(
                    ingredientesNecesarios[ingrediente] ?: 0,
                    cantidadNecesaria
                )
            }
        }

        return ingredientesNecesarios
    }

    private fun verificarIngredientes(
        ingredientesNecesarios: Map<String, Int>
    ): Boolean {

        var stockSuficiente = true

        for ((ingrediente, cantidadNecesaria) in ingredientesNecesarios) {
            val cantidadDisponible = inventario[ingrediente] ?: 0

            when {
                cantidadDisponible >= cantidadNecesaria -> {
                    println("Disponible: $ingrediente")
                }

                else -> {
                    val faltante = cantidadNecesaria - cantidadDisponible

                    println(
                        "Stock insuficiente de $ingrediente. " +
                                "Necesario: $cantidadNecesaria, " +
                                "Disponible: $cantidadDisponible"
                    )

                    println(
                        "Reabastecer $faltante unidades de $ingrediente."
                    )

                    stockSuficiente = false
                }
            }
        }

        return stockSuficiente
    }

    fun verificarStock(
        producto: Producto,
        cantidad: Int
    ): Boolean {
        return verificarStock(
            listOf(producto),
            listOf(cantidad)
        )
    }

    fun verificarStock(
        productos: List<Producto>,
        cantidades: List<Int>
    ): Boolean {

        val ingredientesNecesarios =
            calcularIngredientesNecesarios(productos, cantidades)

        return verificarIngredientes(ingredientesNecesarios)
    }

    fun descontarIngredientes(
        producto: Producto,
        cantidad: Int
    ): Boolean {
        return descontarIngredientes(
            listOf(producto),
            listOf(cantidad)
        )
    }

    fun descontarIngredientes(
        productos: List<Producto>,
        cantidades: List<Int>
    ): Boolean {

        val ingredientesNecesarios =
            calcularIngredientesNecesarios(productos, cantidades)

        if (!verificarIngredientes(ingredientesNecesarios)) {
            println("No se puede preparar el pedido completo.")
            return false
        }

        for ((ingrediente, cantidadNecesaria) in ingredientesNecesarios) {
            inventario[ingrediente] =
                inventario.getValue(ingrediente) - cantidadNecesaria
        }

        println("Inventario actualizado correctamente.")
        return true
    }

    fun descontarIngredientes(pedido: Pedido): Boolean {
        return descontarIngredientes(
            pedido.productos,
            pedido.cantidades
        )
    }

    fun mostrarInventario() {
        println()
        println("=== INVENTARIO ACTUAL ===")

        for ((ingrediente, cantidad) in inventario) {
            println("$ingrediente: $cantidad unidades")
        }
    }


    fun devolverIngredientes(pedido: Pedido) {

        val ingredientesNecesarios = calcularIngredientesNecesarios(
            pedido.productos,
            pedido.cantidades
        )

        for ((ingrediente, cantidad) in ingredientesNecesarios) {
            inventario[ingrediente] = Math.addExact(
                inventario[ingrediente] ?: 0,
                cantidad
            )
        }

        println(
            "Ingredientes del pedido N° ${pedido.idPedido} " +
                    "devueltos al inventario."
        )
    }

}
