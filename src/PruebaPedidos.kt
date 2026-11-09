import modelo.Cliente
import modelo.Pizza
import modelo.Pedido
import modelo.Producto
import utils.Validaciones

fun main() {

    println("=== PRUEBA DE PRODUCTOS Y PEDIDOS ===")

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = Validaciones.normalizarTelefono("912345678"),
        direccion = "Av. Siempre Viva 123",
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

    val productos: List<Producto> = listOf(
        pizzaPepperoni,
        pizzaQueso
    )

    println()
    println("=== PRODUCTOS DISPONIBLES ===")

    for (producto in productos) {
        println()
        println(producto.detallarProducto())
    }

    val pedido = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = productos,
        cantidades = listOf(3, 2),
        tasaImpuesto = 0.19
    )

    println()
    println(pedido.mostrarResumen())

    pedido.cambiarEstado("Listo")

    println()
    println("Estado actualizado: ${pedido.estado}")
}
