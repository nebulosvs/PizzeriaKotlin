import java.time.LocalDate
import utils.Promociones

fun main() {

    println("=== SEMANA 3: PROMOCIONES POR FECHA ===")

    val fechaInicio = LocalDate.of(2026, 11, 10)
    val fechaFin = LocalDate.of(2026, 11, 20)

    val subtotal = 20000.0

    val fechasPrueba = listOf(
        LocalDate.of(2026, 11, 8),
        LocalDate.of(2026, 11, 10),
        LocalDate.of(2026, 11, 15),
        LocalDate.of(2026, 11, 20),
        LocalDate.of(2026, 11, 25)
    )

    for (fecha in fechasPrueba) {

        println()
        println("================================")

        Promociones.mostrarEstadoPromocion(
            fechaActual = fecha,
            fechaInicio = fechaInicio,
            fechaFin = fechaFin
        )

        val descuento =
            Promociones.calcularDescuentoPromocional(
                subtotal = subtotal,
                fechaActual = fecha,
                fechaInicio = fechaInicio,
                fechaFin = fechaFin
            )

        val total = subtotal - descuento

        println("Subtotal: $$subtotal")
        println("Descuento promocional: $$descuento")
        println("Total después de promoción: $$total")
    }
}
