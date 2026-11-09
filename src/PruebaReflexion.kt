import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import modelo.Producto
import utils.ReflectionUtils

fun main() {

    println("=== SEMANA 3: ANOTACIONES Y REFLEXIÓN ===")

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = "912345678",
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

    val pedido = Pedido(
        idPedido = 1,
        cliente = cliente,
        productos = listOf(pizza, bebida),
        cantidades = listOf(2, 1)
    )

    println()
    println("=== IDENTIFICACIÓN DE PROMOCIONES ===")

    val productos = listOf(pizza, bebida)

    for (producto in productos) {
        println()
        ReflectionUtils.mostrarInformacionPromocion(producto)
    }

    println()
    println("=== INSPECCIÓN DINÁMICA DE CLASES ===")

    ReflectionUtils.mostrarEstructuraClase(pizza)
    ReflectionUtils.mostrarEstructuraClase(pedido)
    ReflectionUtils.mostrarEstructuraClase(cliente)
}
