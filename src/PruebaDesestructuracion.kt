import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import utils.Validaciones

fun main() {

    println("=== SEMANA 3: DESESTRUCTURACIÓN ===")

    val telefonoNormalizado =
        Validaciones.normalizarTelefono("912345678")

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = telefonoNormalizado,
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

    val pedido = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(
            pizzaPepperoni,
            pizzaQueso
        ),
        cantidades = listOf(2, 1)
    )

    println()
    println("=== DESESTRUCTURACIÓN DE CLIENTE ===")

    val (nombre, correo, contacto, direccion) = cliente

    println("Nombre: $nombre")
    println("Correo: $correo")
    println("Teléfono: $contacto")
    println("Dirección: $direccion")

    println()
    println("=== DESESTRUCTURACIÓN DE PEDIDO ===")

    val (
        idPedido,
        clientePedido,
        productos,
        cantidades,
        estado,
        total
    ) = pedido

    println("ID del pedido: $idPedido")
    println("Cliente: ${clientePedido.nombre}")
    println("Estado: $estado")

    println()
    println("Productos del pedido:")

    for (indice in productos.indices) {

        val producto = productos[indice]
        val cantidad = cantidades[indice]

        println("- ${producto.nombre} x$cantidad")
    }

    println()
    println("Subtotal: $${pedido.calcularSubtotal()}")
    println("Descuento: $${pedido.calcularDescuento()}")
    println("Impuesto: $${pedido.calcularImpuesto()}")
    println("Total desestructurado: $$total")

    check(Validaciones.validarTelefonoNormalizado(contacto))
    check(contacto == "+56 (912) 345-678")
    check(total == pedido.calcularTotal())
    check(total == 38080.0)

    println()
    println("Todas las pruebas fueron exitosas.")
}
