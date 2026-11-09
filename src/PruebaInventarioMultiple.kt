import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import servicio.InventarioService

fun main() {

    println("=== PRUEBA DE INVENTARIO MÚLTIPLE ===")

    val inventario = mutableMapOf(
        "Masa" to 10,
        "Queso" to 15,
        "Pepperoni" to 12
    )

    val inventarioService = InventarioService(inventario)

    val pizzaPepperoni = Pizza(
        nombre = "Pepperoni",
        precio = 10000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 2,
            "Pepperoni" to 3
        )
    )

    val pizzaQueso = Pizza(
        nombre = "Cuatro Quesos",
        precio = 12000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 4
        )
    )

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = "912345678",
        direccion = "Santiago Centro",
        correo = "pedro@gmail.com"
    )

    val pedido1 = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(pizzaPepperoni, pizzaQueso),
        cantidades = listOf(2, 2)
    )

    val pedido2 = Pedido(
        idPedido = 2,
        cliente = cliente,
        productos = listOf(pizzaPepperoni, pizzaQueso),
        cantidades = listOf(1, 1)
    )

    inventarioService.mostrarInventario()

    println()
    println("=== PEDIDO 1: 2 PEPPERONI Y 2 CUATRO QUESOS ===")

    val resultado1 = inventarioService.descontarIngredientes(pedido1)

    println("Pedido 1 aceptado: $resultado1")

    inventarioService.mostrarInventario()

    println()
    println("=== PEDIDO 2: 1 PEPPERONI Y 1 CUATRO QUESOS ===")

    val resultado2 = inventarioService.descontarIngredientes(pedido2)

    println("Pedido 2 aceptado: $resultado2")

    inventarioService.mostrarInventario()
}
