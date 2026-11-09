
import modelo.Pizza
import servicio.InventarioService

fun main() {

    val inventario = mutableMapOf(
        "Masa" to 10,
        "Queso" to 15,
        "Pepperoni" to 12
    )

    val inventarioService = InventarioService(inventario)

    val pizza = Pizza(
        nombre = "Pepperoni",
        precio = 10000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 2,
            "Pepperoni" to 3
        )
    )

    inventarioService.mostrarInventario()

    println()
    println("=== PEDIDO DE 3 PIZZAS ===")

    inventarioService.descontarIngredientes(pizza, 3)

    inventarioService.mostrarInventario()

    println()
    println("=== PEDIDO DE 5 PIZZAS ===")

    inventarioService.descontarIngredientes(pizza, 5)

    inventarioService.mostrarInventario()
}
