package servicio

import modelo.Pedido

class PedidoService {

    private val pedidos = mutableListOf<Pedido>()


    fun agregarPedido(pedido: Pedido) {

        if (pedidos.any { it.idPedido == pedido.idPedido }) {
            println(
                "Ya existe un pedido con el ID ${pedido.idPedido}."
            )
            return
        }

        pedidos.add(pedido)

        when (pedido.estado) {

            "Rechazado" -> {
                println(
                    "Pedido N° ${pedido.idPedido} registrado " +
                            "en el historial como rechazado."
                )
            }

            else -> {
                println(
                    "Pedido N° ${pedido.idPedido} " +
                            "registrado correctamente."
                )
            }
        }
    }


    fun mostrarPedidosDelDia() {
        println()
        println("=== PEDIDOS DEL DÍA ===")

        if (pedidos.isEmpty()) {
            println("No hay pedidos registrados.")
            return
        }

        for (pedido in pedidos) {
            println()
            println("Pedido N°: ${pedido.idPedido}")
            println("Cliente: ${pedido.cliente.nombre}")

            for (indice in pedido.productos.indices) {
                val producto = pedido.productos[indice]
                val cantidad = pedido.cantidades[indice]

                println("Producto: ${producto.nombre} x$cantidad")
            }

            println("Total: $${pedido.calcularTotal()}")
            println("Estado: ${pedido.estado}")
        }
    }

    fun obtenerPedidosCompletados(): List<Pedido> {
        return pedidos.filter { pedido ->
            pedido.estado == "Entregado"
        }
    }


    fun obtenerPedidosPendientes(): List<Pedido> {

        return pedidos.filter {
            it.estado != "Entregado" &&
                    it.estado != "Cancelado" &&
                    it.estado != "Rechazado"
        }
    }


    fun obtenerPedidosCancelados(): List<Pedido> {
        return pedidos.filter { pedido ->
            pedido.estado == "Cancelado"
        }
    }


    fun obtenerPedidosRechazados(): List<Pedido> {
        return pedidos.filter { it.estado == "Rechazado" }
    }



    fun mostrarResumenEstados() {

        println()
        println("=== RESUMEN DE ESTADOS ===")

        println(
            "Completados: ${obtenerPedidosCompletados().size}"
        )

        println(
            "Pendientes: ${obtenerPedidosPendientes().size}"
        )

        println(
            "Cancelados: ${obtenerPedidosCancelados().size}"
        )

        println(
            "Rechazados: ${obtenerPedidosRechazados().size}"
        )
    }



    fun buscarPedidoPorId(idPedido: Int): Pedido? {
        return pedidos.find { it.idPedido == idPedido }
    }

}
