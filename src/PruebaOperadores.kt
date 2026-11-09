import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import utils.Validaciones

fun main() {

    println("=== SEMANA 3: SOBRECARGA DE OPERADORES ===")

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = Validaciones.normalizarTelefono("912345678"),
        direccion = "Santiago Centro",
        correo = "pedro@gmail.com"
    )

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

    val pedido1 = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(pizzaPepperoni),
        cantidades = listOf(2)
    )

    val pedido2 = Pedido(
        idPedido = 2,
        cliente = cliente,
        productos = listOf(pizzaQueso),
        cantidades = listOf(3)
    )

    println()
    println("=== PEDIDO 1 ===")
    println(pedido1.mostrarResumen())

    println()
    println("=== PEDIDO 2 ===")
    println(pedido2.mostrarResumen())

    println()
    println("=== COMBINACIÓN DE PEDIDOS ===")

    val pedidoCombinado = pedido1 + pedido2

    println(pedidoCombinado.mostrarResumen())

    println()
    println("Cantidad total de pizzas: " +
            pedidoCombinado.cantidades.sum())
}
