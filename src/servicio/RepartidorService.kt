package servicio

import modelo.Pedido
import modelo.Repartidor

class RepartidorService(
    private val repartidores: List<Repartidor>
) {

    fun buscarRepartidor(zona: String): Repartidor? {
        return repartidores.find { repartidor ->
            repartidor.disponible &&
                    repartidor.zonaReparto.equals(
                        zona.trim(),
                        ignoreCase = true
                    )
        }
    }

    fun asignarRepartidor(
        pedido: Pedido,
        zona: String
    ): Repartidor? {

        if (pedido.estado != "Listo") {
            println("El pedido debe estar listo antes de asignar un repartidor.")
            return null
        }

        val repartidor = buscarRepartidor(zona)

        return if (repartidor != null) {
            println(
                "Repartidor asignado: ${repartidor.nombre}"
            )

            println(repartidor.entregarPedido(pedido.idPedido))

            pedido.cambiarEstado("En camino")

            repartidor
        } else {
            println(
                "No hay repartidores disponibles para la zona: $zona"
            )
            null
        }
    }

    fun mostrarRepartidores() {
        println("=== REPARTIDORES ===")

        for (repartidor in repartidores) {
            println(
                "${repartidor.nombre} | " +
                        "Zona: ${repartidor.zonaReparto} | " +
                        "Disponible: ${repartidor.disponible}"
            )
        }
    }
}
