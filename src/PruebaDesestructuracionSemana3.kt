import modelo.Cliente
import modelo.Pedido
import modelo.Pizza

fun main() {

    println("=== PRUEBA DE DESESTRUCTURACIÓN ===")

    val cliente = Cliente(
        nombre = "Sofía",
        contacto = "+56 (912) 345-678",
        direccion = "Santiago Centro",
        correo = "sofia@gmail.com"
    )

    // Desestructuración de Cliente
    val (nombre, correo, telefono) = cliente

    println()
    println("=== CLIENTE DESESTRUCTURADO ===")
    println("Nombre: $nombre")
    println("Correo: $correo")
    println("Teléfono: $telefono")

    val pizza = Pizza(
        nombre = "Pepperoni",
        precio = 10000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 2,
            "Pepperoni" to 3
        )
    )

    val pedido = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(pizza),
        cantidades = listOf(2)
    )

    // Desestructuración de Pedido
    val (_, clientePedido, productosPedido, _, _, total) = pedido

    println()
    println("=== PEDIDO DESESTRUCTURADO ===")
    println("Cliente: ${clientePedido.nombre}")
    println(
        "Productos: ${productosPedido.joinToString { it.nombre }}"
    )
    println("Total: $$total")

    check(nombre == "Sofía")
    check(correo == "sofia@gmail.com")
    check(telefono == "+56 (912) 345-678")
    check(clientePedido == cliente)
    check(productosPedido == listOf(pizza))
    check(total == 23800.0)

    println()
    println("Todas las pruebas de desestructuración fueron exitosas.")
}
