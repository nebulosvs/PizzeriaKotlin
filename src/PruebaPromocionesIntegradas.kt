import java.time.LocalDate
import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import modelo.Producto
import utils.Validaciones

fun main() {

    println("=== SEMANA 3: PROMOCIONES INTEGRADAS ===")

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = Validaciones.normalizarTelefono("912345678"),
        direccion = "Santiago Centro",
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

    val bebida = Producto(
        nombre = "Bebida",
        precio = 2000.0,
        categoria = "Bebidas"
    )

    val fechaInicio = LocalDate.of(2026, 11, 10)
    val fechaFin = LocalDate.of(2026, 11, 20)

    // PRUEBA 1: Fuera del período promocional

    val pedido1 = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(pizza, bebida),
        cantidades = listOf(2, 1)
    )

    pedido1.fechaPedido = LocalDate.of(2026, 11, 8)
    pedido1.fechaInicioPromocion = fechaInicio
    pedido1.fechaFinPromocion = fechaFin

    println()
    println("=== PRUEBA 1: SIN PROMOCIÓN ===")
    println(pedido1.mostrarResumen())

    // PRUEBA 2: Dentro del período promocional

    val pedido2 = Pedido(
        idPedido = 2,
        cliente = cliente,
        productos = listOf(pizza, bebida),
        cantidades = listOf(2, 1)
    )

    pedido2.fechaPedido = LocalDate.of(2026, 11, 15)
    pedido2.fechaInicioPromocion = fechaInicio
    pedido2.fechaFinPromocion = fechaFin

    println()
    println("=== PRUEBA 2: PROMOCIÓN ACTIVA ===")
    println(pedido2.mostrarResumen())

    // PRUEBA 3: Descuento por cantidad

    val pedido3 = Pedido(
        idPedido = 3,
        cliente = cliente,
        productos = listOf(pizza),
        cantidades = listOf(5)
    )

    pedido3.fechaPedido = LocalDate.of(2026, 11, 8)
    pedido3.fechaInicioPromocion = fechaInicio
    pedido3.fechaFinPromocion = fechaFin

    println()
    println("=== PRUEBA 3: DESCUENTO POR CANTIDAD ===")
    println(pedido3.mostrarResumen())

    // PRUEBA 4: Ambos descuentos disponibles

    val pedido4 = Pedido(
        idPedido = 4,
        cliente = cliente,
        productos = listOf(pizza),
        cantidades = listOf(5)
    )

    pedido4.fechaPedido = LocalDate.of(2026, 11, 15)
    pedido4.fechaInicioPromocion = fechaInicio
    pedido4.fechaFinPromocion = fechaFin

    println()
    println("=== PRUEBA 4: MAYOR DESCUENTO ===")
    println(pedido4.mostrarResumen())
}
