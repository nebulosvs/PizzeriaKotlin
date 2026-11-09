import modelo.Cliente
import modelo.Pizza
import modelo.Producto

fun main() {

    println("=== SEMANA 3: EQUALS Y HASHCODE ===")

    val pizza1 = Pizza(
        nombre = "Pepperoni",
        precio = 10000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 2,
            "Pepperoni" to 3
        )
    )

    val pizza2 = Pizza(
        nombre = "Pepperoni",
        precio = 12000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 3
        )
    )

    val pizza3 = Pizza(
        nombre = "Cuatro Quesos",
        precio = 12000.0,
        ingredientes = mapOf(
            "Masa" to 1,
            "Queso" to 4
        )
    )

    val bebida = Producto(
        nombre = "Pepperoni",
        precio = 2000.0,
        categoria = "Bebidas"
    )

    println()
    println("=== COMPARACIÓN DE PRODUCTOS ===")

    println("Pizza 1 == Pizza 2: ${pizza1 == pizza2}")
    println("Pizza 1 == Pizza 3: ${pizza1 == pizza3}")
    println("Pizza 1 == Bebida: ${pizza1 == bebida}")

    println()
    println("HashCode Pizza 1: ${pizza1.hashCode()}")
    println("HashCode Pizza 2: ${pizza2.hashCode()}")

    println(
        "HashCode iguales: " +
                (pizza1.hashCode() == pizza2.hashCode())
    )

    val cliente1 = Cliente(
        nombre = "Pedro",
        contacto = "912345678",
        direccion = "Santiago Centro",
        correo = "pedro@gmail.com"
    )

    val cliente2 = Cliente(
        nombre = "Pedro González",
        contacto = "987654321",
        direccion = "Providencia",
        correo = "PEDRO@gmail.com"
    )

    val cliente3 = Cliente(
        nombre = "María",
        contacto = "923456789",
        direccion = "Las Condes",
        correo = "maria@gmail.com"
    )

    println()
    println("=== COMPARACIÓN DE CLIENTES ===")

    println("Cliente 1 == Cliente 2: ${cliente1 == cliente2}")
    println("Cliente 1 == Cliente 3: ${cliente1 == cliente3}")

    println()
    println("HashCode Cliente 1: ${cliente1.hashCode()}")
    println("HashCode Cliente 2: ${cliente2.hashCode()}")

    println(
        "HashCode iguales: " +
                (cliente1.hashCode() == cliente2.hashCode())
    )

    println()
    println("=== PRUEBA CON SET ===")

    val productosUnicos = setOf(
        pizza1,
        pizza2,
        pizza3,
        bebida
    )

    println("Productos ingresados: 4")
    println("Productos únicos: ${productosUnicos.size}")

    val clientesUnicos = setOf(
        cliente1,
        cliente2,
        cliente3
    )

    println("Clientes ingresados: 3")
    println("Clientes únicos: ${clientesUnicos.size}")
}
