package utils

import java.time.LocalDate

object Promociones {

    private const val DESCUENTO_PROMOCIONAL = 0.15

    fun estaEnPeriodoPromocional(
        fechaActual: LocalDate,
        fechaInicio: LocalDate,
        fechaFin: LocalDate
    ): Boolean {

        require(!fechaInicio.isAfter(fechaFin)) {
            "La fecha de inicio no puede ser posterior a la fecha de término."
        }

        return fechaActual in fechaInicio..fechaFin
    }

    fun calcularDescuentoPromocional(
        subtotal: Double,
        fechaActual: LocalDate,
        fechaInicio: LocalDate,
        fechaFin: LocalDate
    ): Double {

        require(subtotal >= 0 && subtotal.isFinite()) {
            "El subtotal debe ser un número válido y no negativo."
        }

        return if (
            estaEnPeriodoPromocional(
                fechaActual,
                fechaInicio,
                fechaFin
            )
        ) {
            subtotal * DESCUENTO_PROMOCIONAL
        } else {
            0.0
        }
    }

    fun mostrarEstadoPromocion(
        fechaActual: LocalDate,
        fechaInicio: LocalDate,
        fechaFin: LocalDate
    ) {

        println("Fecha evaluada: $fechaActual")
        println("Inicio de promoción: $fechaInicio")
        println("Fin de promoción: $fechaFin")

        when {
            fechaActual < fechaInicio -> {
                println("La promoción todavía no comienza.")
            }

            fechaActual in fechaInicio..fechaFin -> {
                println("Promoción activa: 15% de descuento.")
            }

            else -> {
                println("La promoción ha finalizado.")
            }
        }
    }


    fun calcularDescuentoProductos(
        productos: List<modelo.Producto>,
        cantidades: List<Int>,
        fechaActual: LocalDate,
        fechaInicio: LocalDate,
        fechaFin: LocalDate
    ): Double {

        require(productos.size == cantidades.size) {
            "Cada producto debe tener una cantidad asociada."
        }

        require(cantidades.all { it > 0 }) {
            "Las cantidades deben ser positivas."
        }

        if (!estaEnPeriodoPromocional(
                fechaActual,
                fechaInicio,
                fechaFin
            )
        ) {
            return 0.0
        }

        var descuentoTotal = 0.0

        for (indice in productos.indices) {

            val producto = productos[indice]
            val cantidad = cantidades[indice]

            if (ReflectionUtils.esPromocionable(producto)) {

                val porcentaje =
                    ReflectionUtils.obtenerPorcentajePromocion(producto)

                require(porcentaje in 0..100) {
                    "El porcentaje promocional debe estar entre 0 y 100."
                }

                val subtotalProducto = producto.precio * cantidad

                descuentoTotal += subtotalProducto * porcentaje / 100.0
            }
        }

        return descuentoTotal
    }

}
