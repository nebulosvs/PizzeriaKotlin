import utils.Validaciones

fun main() {

    println("=== PRUEBA DE VALIDACIONES ===")

    val nombre = Validaciones.leerTexto(
        "Nombre del cliente: "
    )

    val telefono = Validaciones.leerTelefono(
        "Teléfono del cliente: "
    )

    val correo = Validaciones.leerCorreo(
        "Correo electrónico: "
    )

    val precio = Validaciones.leerPrecio(
        "Precio de la pizza: "
    )

    val cantidad = Validaciones.leerCantidad(
        "Cantidad de pizzas: "
    )

    println()
    println("=== DATOS VALIDADOS ===")
    println("Nombre: $nombre")
    println("Teléfono: $telefono")
    println("Correo: $correo")
    println("Precio: $$precio")
    println("Cantidad: $cantidad")

    println()
    println("=== PRUEBA DE INVENTARIO ===")

    Validaciones.validarInventario(10)
    Validaciones.validarInventario(-5)

    println()
    println("=== PRUEBA DE NULL SAFETY ===")

    Validaciones.mostrarObservacion(null)

    Validaciones.mostrarObservacion(
        "Entregar sin tocar el timbre."
    )
}
