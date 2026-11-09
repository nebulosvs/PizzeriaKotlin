import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import servicio.PedidoService

fun main() {

    println("=== PRUEBA DE HISTORIAL DE PEDIDOS ===")

    val cliente1 = Cliente(
        nombre = "Pedro",
        contacto = "912345678",
        direccion = "Santiago Centro",
        correo = "pedro@gmail.com"
    )

    val cliente2 = Cliente(
        nombre = "María",
        contacto = "987654321",
        direccion = "Providencia",
        correo = "maria@gmail.com"
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
        cliente = cliente1,
        productos = listOf(pizzaPepperoni),
        cantidades = listOf(2)
    )

    val pedido2 = Pedido(
        idPedido = 2,
        cliente = cliente2,
        productos = listOf(pizzaQueso),
        cantidades = listOf(4)
    )

    val pedido3 = Pedido(
        idPedido = 3,
        cliente = cliente1,
        productos = listOf(pizzaPepperoni, pizzaQueso),
        cantidades = listOf(1, 1)
    )

    val pedido4 = Pedido(
        idPedido = 4,
        cliente = cliente2,
        productos = listOf(pizzaPepperoni),
        cantidades = listOf(1)
    )

    pedido1.cambiarEstado("Entregado")
    pedido2.cambiarEstado("En camino")
    pedido3.cambiarEstado("Listo")
    pedido4.cambiarEstado("Cancelado")

    val pedidoService = PedidoService()

    pedidoService.agregarPedido(pedido1)
    pedidoService.agregarPedido(pedido2)
    pedidoService.agregarPedido(pedido3)
    pedidoService.agregarPedido(pedido4)

    pedidoService.mostrarPedidosDelDia()
    pedidoService.mostrarResumenEstados()

    println()
    println("=== PEDIDOS COMPLETADOS ===")

    for (pedido in pedidoService.obtenerPedidosCompletados()) {
        println("Pedido N° ${pedido.idPedido}: ${pedido.cliente.nombre}")
    }

    println()
    println("=== PEDIDOS PENDIENTES ===")

    for (pedido in pedidoService.obtenerPedidosPendientes()) {
        println("Pedido N° ${pedido.idPedido}: ${pedido.estado}")
    }

    println()
    println("=== PEDIDOS CANCELADOS ===")

    for (pedido in pedidoService.obtenerPedidosCancelados()) {
        println("Pedido N° ${pedido.idPedido}: ${pedido.estado}")
    }
}
