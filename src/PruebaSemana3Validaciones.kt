
import utils.Validaciones

fun main() {

    println("=== SEMANA 3: VALIDACIÓN DE TELÉFONOS ===")

    val telefonos = listOf(
        "569123456789",
        "+56 912 345 6789",
        "+56 (912) 345-6789",
        "12345"
    )

    for (telefono in telefonos) {

        println()
        println("Teléfono original: $telefono")

        try {

            val telefonoNormalizado =
                Validaciones.normalizarTelefono(telefono)

            println("Normalizado: $telefonoNormalizado")

            val esValido =
                Validaciones.validarTelefonoNormalizado(
                    telefonoNormalizado
                )

            println("Formato válido: $esValido")

        } catch (e: IllegalArgumentException) {

            println("Error: ${e.message}")
        }
    }
}
