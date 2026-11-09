import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import modelo.Repartidor
import servicio.RepartidorService

fun main() {

    println("=== PRUEBA DE REPARTIDORES ===")

    val repartidores = listOf(
        Repartidor(
            nombre = "Carlos Palacios",
            contacto = "987654321",
            zonaReparto = "Santiago Centro"
        ),
        Repartidor(
            nombre = "María González",
            contacto = "912345678",
            zonaReparto = "Providencia"
        ),
        Repartidor(
            nombre = "Juan Pérez",
            contacto = "956789123",
            zonaReparto = "Santiago Centro"
        )
    )

    val repartidorService = RepartidorService(repartidores)

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = "912345678",
        direccion = "Av. Siempre Viva 123",
        correo = "pedro@gmail.com"
    )

    val pizza = Pizza(
        nombre = "Pepperoni",
        precio = 10000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 2,
            "Pepperoni" to 3
        )
    )

    val pedido1 = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(pizza),
        cantidades = listOf(2)
    )

    val pedido2 = Pedido(
        idPedido = 2,
        cliente = cliente,
        productos = listOf(pizza),
        cantidades = listOf(1)
    )

    val pedido3 = Pedido(
        idPedido = 3,
        cliente = cliente,
        productos = listOf(pizza),
        cantidades = listOf(1)
    )

    repartidorService.mostrarRepartidores()

    println()
    println("=== ASIGNACIÓN PEDIDO 1 ===")

    pedido1.cambiarEstado("Listo")

    repartidorService.asignarRepartidor(
        pedido1,
        "Santiago Centro"
    )

    println("Estado pedido 1: ${pedido1.estado}")

    println()
    println("=== ASIGNACIÓN PEDIDO 2 ===")

    pedido2.cambiarEstado("Listo")

    repartidorService.asignarRepartidor(
        pedido2,
        "Santiago Centro"
    )

    println("Estado pedido 2: ${pedido2.estado}")

    println()
    println("=== ASIGNACIÓN PEDIDO 3 ===")

    pedido3.cambiarEstado("Listo")

    repartidorService.asignarRepartidor(
        pedido3,
        "Santiago Centro"
    )

    println("Estado pedido 3: ${pedido3.estado}")

    println()
    repartidorService.mostrarRepartidores()
}
