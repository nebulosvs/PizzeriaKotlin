package modelo

import java.time.LocalDate
import utils.Promociones

class Pedido(
    val idPedido: Int,
    val cliente: Cliente,
    val productos: List<Producto>,
    val cantidades: List<Int>,
    var estado: String = "En preparación",
    val tasaImpuesto: Double = 0.19
) {

    init {
        require(idPedido > 0) {
            "El ID del pedido debe ser positivo."
        }

        require(productos.isNotEmpty()) {
            "El pedido debe contener al menos un producto."
        }

        require(productos.size == cantidades.size) {
            "Cada producto debe tener una cantidad asociada."
        }

        require(cantidades.all { it > 0 }) {
            "Las cantidades deben ser mayores que cero."
        }

        require(tasaImpuesto in 0.0..1.0) {
            "La tasa de impuesto debe estar entre 0 y 1."
        }
    }

    var fechaPedido: LocalDate = LocalDate.now()

    var fechaInicioPromocion: LocalDate? = null

    var fechaFinPromocion: LocalDate? = null


    fun calcularSubtotal(): Double {
        return productos.indices.sumOf { indice ->
            productos[indice].precio * cantidades[indice]
        }
    }


    fun calcularDescuento(): Double {

        val descuentoCantidad = if (cantidades.sum() > 3) {
            calcularSubtotal() * 0.10
        } else {
            0.0
        }

        val inicio = fechaInicioPromocion
        val fin = fechaFinPromocion

        val descuentoPromocional =
            if (inicio != null && fin != null) {

                Promociones.calcularDescuentoProductos(
                    productos = productos,
                    cantidades = cantidades,
                    fechaActual = fechaPedido,
                    fechaInicio = inicio,
                    fechaFin = fin
                )

            } else {
                0.0
            }

        return maxOf(descuentoCantidad, descuentoPromocional)
    }


    fun calcularImpuesto(): Double {
        val montoNeto = calcularSubtotal() - calcularDescuento()
        return montoNeto * tasaImpuesto
    }


    fun calcularTotal(): Double {

        if (estado == "Cancelado" || estado == "Rechazado") {
            return 0.0
        }

        return calcularSubtotal() -
                calcularDescuento() +
                calcularImpuesto()
    }



    fun cambiarEstado(nuevoEstado: String) {

        val estadosValidos = listOf(
            "En preparación",
            "Listo",
            "En camino",
            "Entregado",
            "Cancelado",
            "Rechazado"
        )

        if (nuevoEstado in estadosValidos) {
            estado = nuevoEstado
        } else {
            println("Error: Estado de pedido no válido.")
        }
    }


    fun mostrarResumen(): String {

        val detalleProductos = productos.indices.joinToString("\n") { indice ->
            val producto = productos[indice]
            val cantidad = cantidades[indice]
            val subtotalProducto = producto.precio * cantidad

            "${producto.nombre} x$cantidad - $$subtotalProducto"
        }

        return buildString {
            appendLine("===== RESUMEN DEL PEDIDO =====")
            appendLine("Pedido N°: $idPedido")
            appendLine("Cliente: ${cliente.nombre}")
            appendLine("Teléfono: ${cliente.contacto}")
            appendLine("Correo: ${cliente.correo}")
            appendLine()
            appendLine("Productos:")
            appendLine(detalleProductos)
            appendLine()
            appendLine("Subtotal: $${calcularSubtotal()}")
            appendLine("Descuento: $${calcularDescuento()}")
            appendLine("Impuesto: $${calcularImpuesto()}")
            appendLine("Total: $${calcularTotal()}")
            appendLine("Estado: $estado")
            append("================================")
        }
    }



    operator fun plus(otro: Pedido): Pedido {

        require(cliente == otro.cliente) {
            "No se pueden combinar pedidos de clientes diferentes."
        }

        require(
            estado == "En preparación" &&
                    otro.estado == "En preparación"
        ) {
            "Solo se pueden combinar pedidos en preparación."
        }

        require(tasaImpuesto == otro.tasaImpuesto) {
            "Los pedidos deben utilizar la misma tasa de impuesto."
        }

        require(fechaPedido == otro.fechaPedido) {
            "Los pedidos deben tener la misma fecha."
        }

        require(
            fechaInicioPromocion == otro.fechaInicioPromocion &&
                    fechaFinPromocion == otro.fechaFinPromocion
        ) {
            "Los pedidos deben tener el mismo período promocional."
        }

        val pedidoCombinado = Pedido(
            idPedido = idPedido,
            cliente = cliente,
            productos = productos + otro.productos,
            cantidades = cantidades + otro.cantidades,
            estado = "En preparación",
            tasaImpuesto = tasaImpuesto
        )

        pedidoCombinado.fechaPedido = fechaPedido
        pedidoCombinado.fechaInicioPromocion = fechaInicioPromocion
        pedidoCombinado.fechaFinPromocion = fechaFinPromocion

        return pedidoCombinado
    }



    operator fun component1(): Int {
        return idPedido
    }

    operator fun component2(): Cliente {
        return cliente
    }

    operator fun component3(): List<Producto> {
        return productos
    }

    operator fun component4(): List<Int> {
        return cantidades
    }

    operator fun component5(): String {
        return estado
    }


    operator fun component6(): Double {
        return calcularTotal()
    }


}
