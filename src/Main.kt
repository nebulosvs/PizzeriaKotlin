import modelo.Cliente
import modelo.Pedido
import modelo.Pizza
import modelo.Repartidor
import servicio.InventarioService
import servicio.PedidoService
import servicio.RepartidorService
import utils.Validaciones
import java.time.LocalDate

import utils.ReflectionUtils


fun main() {

    val inventarioService = InventarioService(
        mutableMapOf(
            "Masa" to 30,
            "Queso" to 60,
            "Pepperoni" to 40,
            "Jamón" to 30,
            "Champiñones" to 25
        )
    )

    val catalogo = listOf(
        Pizza(
            nombre = "Pepperoni",
            precio = 10000.0,
            ingredientes = mapOf(
                "Masa" to 1,
                "Queso" to 2,
                "Pepperoni" to 3
            )
        ),
        Pizza(
            nombre = "Cuatro Quesos",
            precio = 12000.0,
            ingredientes = mapOf(
                "Masa" to 1,
                "Queso" to 4
            )
        ),
        Pizza(
            nombre = "Jamón y Champiñones",
            precio = 11000.0,
            ingredientes = mapOf(
                "Masa" to 1,
                "Queso" to 2,
                "Jamón" to 2,
                "Champiñones" to 2
            )
        )
    )


    require(catalogo.size == catalogo.toSet().size) {
        "Error: El catálogo contiene productos duplicados."
    }


    val repartidores = listOf(
        Repartidor(
            nombre = "Carlos Palacios",
            contacto = "987654321",
            zonaReparto = "Santiago Centro"
        ),
        Repartidor(
            nombre = "María González",
            contacto = "912345678",
            zonaReparto = "Providencia"
        ),
        Repartidor(
            nombre = "Juan Pérez",
            contacto = "956789123",
            zonaReparto = "Santiago Centro"
        )
    )

    val pedidoService = PedidoService()
    val repartidorService = RepartidorService(repartidores)

    val clientesRegistrados = mutableSetOf<Cliente>()


    val repartidoresAsignados = mutableMapOf<Int, Repartidor>()

    var siguienteIdPedido = 1


    val fechaInicioPromocion = LocalDate.of(2026, 11, 10)
    val fechaFinPromocion = LocalDate.of(2026, 11, 20)


    while (true) {

        println()
        println("========== PIZZERÍA KOTLIN ==========")
        println("1. Registrar nuevo pedido")
        println("2. Mostrar inventario")
        println("3. Mostrar pedidos del día")
        println("4. Mostrar resumen de estados")
        println("5. Mostrar repartidores")
        println("6. Actualizar estado de un pedido")
        println("7. Demostración de Kotlin avanzado")
        println("0. Salir")
        println("=====================================")
        print("Seleccione una opción: ")

        when (readlnOrNull()?.trim()) {

            "1" -> {

                println()
                println("=== REGISTRAR CLIENTE ===")

                val nombre = Validaciones.leerTexto(
                    "Nombre: "
                )

                val telefono = Validaciones.leerTelefono(
                    "Teléfono: "
                )

                val correo = Validaciones.leerCorreo(
                    "Correo: "
                )

                val direccion = Validaciones.leerTexto(
                    "Dirección: "
                )

                val nuevoCliente = Cliente(
                    nombre = nombre,
                    contacto = telefono,
                    direccion = direccion,
                    correo = correo
                )

                val clienteExistente = clientesRegistrados.find {
                    it == nuevoCliente
                }

                val cliente = if (clienteExistente != null) {

                    println()
                    println("Cliente ya registrado.")
                    println("Se utilizarán sus datos existentes.")

                    clienteExistente

                } else {

                    clientesRegistrados.add(nuevoCliente)

                    println()
                    println("Cliente registrado correctamente.")

                    nuevoCliente
                }


                val productosSeleccionados = mutableListOf<Pizza>()
                val cantidadesSeleccionadas = mutableListOf<Int>()

                var agregarMas = true

                while (agregarMas) {

                    println()
                    println("=== CATÁLOGO DE PIZZAS ===")

                    for ((indice, pizza) in catalogo.withIndex()) {
                        println(
                            "${indice + 1}. ${pizza.nombre} - $${pizza.precio}"
                        )
                    }

                    print("Seleccione una pizza: ")

                    val opcionPizza =
                        readlnOrNull()?.trim()?.toIntOrNull()

                    if (opcionPizza == null ||
                        opcionPizza !in 1..catalogo.size
                    ) {
                        println("Opción de pizza inválida.")
                        continue
                    }

                    val pizzaSeleccionada = catalogo[opcionPizza - 1]

                    val cantidad = Validaciones.leerCantidad(
                        "Cantidad: "
                    )

                    productosSeleccionados.add(pizzaSeleccionada)
                    cantidadesSeleccionadas.add(cantidad)

                    print("¿Desea agregar otra pizza? (s/n): ")

                    agregarMas = readlnOrNull()
                        ?.trim()
                        ?.equals("s", ignoreCase = true) == true
                }

                val pedido = Pedido(
                    idPedido = siguienteIdPedido,
                    cliente = cliente,
                    productos = productosSeleccionados,
                    cantidades = cantidadesSeleccionadas
                )


                pedido.fechaPedido = LocalDate.now()

                pedido.fechaInicioPromocion = fechaInicioPromocion
                pedido.fechaFinPromocion = fechaFinPromocion


                println()
                println("=== INFORMACIÓN PROMOCIONAL ===")
                println("Fecha del pedido: ${pedido.fechaPedido}")

                val promocionActiva =
                    pedido.fechaPedido in
                            fechaInicioPromocion..fechaFinPromocion

                if (promocionActiva) {
                    println("Promoción por fecha activa.")
                    println("Se aplicarán descuentos a productos elegibles.")
                } else {
                    println("No hay promoción por fecha activa.")
                }

                println(
                    "Descuento calculado: $${pedido.calcularDescuento()}"
                )


                println()
                println("=== VERIFICACIÓN DE INVENTARIO ===")

                val stockDisponible =
                    inventarioService.descontarIngredientes(pedido)

                if (stockDisponible) {

                    pedidoService.agregarPedido(pedido)
                    siguienteIdPedido++

                    println()
                    println("Pedido registrado correctamente.")
                    println(pedido.mostrarResumen())

                    println()
                    println(
                        "Pedido registrado en estado: ${pedido.estado}"
                    )

                    println(
                        "Cuando esté preparado, utilice la opción 6 " +
                                "para cambiarlo a Listo."
                    )

                } else {

                    pedido.cambiarEstado("Rechazado")

                    pedidoService.agregarPedido(pedido)
                    siguienteIdPedido++

                    println()
                    println(
                        "Pedido N° ${pedido.idPedido} rechazado " +
                                "por falta de ingredientes."
                    )

                    println("No se descontaron ingredientes del inventario.")
                    println("Total a pagar: $${pedido.calcularTotal()}")
                    println()
                    println(pedido.mostrarResumen())
                }
            }

            "2" -> {
                inventarioService.mostrarInventario()
            }

            "3" -> {
                pedidoService.mostrarPedidosDelDia()
            }

            "4" -> {
                pedidoService.mostrarResumenEstados()
            }

            "5" -> {
                repartidorService.mostrarRepartidores()
            }

            "6" -> {

                val idPedido = Validaciones.leerIdPedido(
                    "Ingrese el ID del pedido: "
                )

                val pedido = pedidoService.buscarPedidoPorId(idPedido)

                if (pedido == null) {
                    println("No existe un pedido con ese ID.")
                    continue
                }

                println("Estado actual: ${pedido.estado}")
                println()
                println("1. Listo")
                println("2. En camino")
                println("3. Entregado")
                println("4. Cancelado")
                print("Seleccione nuevo estado: ")

                when (readlnOrNull()?.trim()) {

                    "1" -> {
                        if (pedido.estado == "En preparación") {
                            pedido.cambiarEstado("Listo")
                            println("Pedido marcado como Listo.")
                        } else {
                            println("No se puede aplicar este cambio.")
                        }
                    }

                    "2" -> {
                        if (pedido.estado == "Listo") {

                            val zona = Validaciones.leerTexto(
                                "Zona de reparto: "
                            )

                            val repartidor =
                                repartidorService.asignarRepartidor(
                                    pedido,
                                    zona
                                )

                            if (repartidor != null) {
                                repartidoresAsignados[pedido.idPedido] =
                                    repartidor
                            }

                        } else {
                            println(
                                "El pedido debe estar Listo " +
                                        "para iniciar el reparto."
                            )
                        }
                    }

                    "3" -> {
                        if (pedido.estado == "En camino") {

                            pedido.cambiarEstado("Entregado")

                            repartidoresAsignados
                                .remove(pedido.idPedido)
                                ?.let { repartidor ->

                                    repartidor.disponible = true

                                    println(
                                        "Repartidor ${repartidor.nombre} " +
                                                "nuevamente disponible."
                                    )
                                }

                            println("Pedido entregado correctamente.")

                        } else {
                            println(
                                "Solo se pueden entregar pedidos En camino."
                            )
                        }
                    }


                    "4" -> {
                        if (pedido.estado == "En preparación") {

                            inventarioService.devolverIngredientes(pedido)

                            pedido.cambiarEstado("Cancelado")

                            println("Pedido cancelado correctamente.")
                            println("Total a pagar: $${pedido.calcularTotal()}")

                        } else {
                            println(
                                "Solo se permite cancelar pedidos " +
                                        "En preparación."
                            )
                        }
                    }


                    else -> {
                        println("Opción inválida.")
                    }
                }
            }


            "7" -> {

                println()
                println("======================================")
                println("   DEMOSTRACIÓN DE KOTLIN AVANZADO")
                println("======================================")

                // 1. Igualdad y prevención de duplicados

                println()
                println("=== 1. IGUALDAD DE OBJETOS ===")

                val clienteDemo = Cliente(
                    nombre = "Cliente Demo",
                    contacto = "+56 (912) 345-678",
                    direccion = "Santiago Centro",
                    correo = "demo@correo.com"
                )

                val clienteDuplicado = Cliente(
                    nombre = "Cliente Demo",
                    contacto = "+56 (912) 345-678",
                    direccion = "Santiago Centro",
                    correo = "DEMO@CORREO.COM"
                )

                println("Clientes iguales: ${clienteDemo == clienteDuplicado}")

                val clientesDemo = mutableSetOf(
                    clienteDemo,
                    clienteDuplicado
                )

                println("Clientes únicos: ${clientesDemo.size}")

                val pizzaOriginal = catalogo[0]

                val pizzaDuplicada = Pizza(
                    nombre = pizzaOriginal.nombre,
                    precio = pizzaOriginal.precio + 1000.0,
                    ingredientes = pizzaOriginal.ingredientes
                )

                println("Productos iguales: ${pizzaOriginal == pizzaDuplicada}")

                val productosUnicos = setOf(pizzaOriginal, pizzaDuplicada)

                println("Productos únicos: ${productosUnicos.size}")

                // 2. Desestructuración de Cliente

                println()
                println("=== 2. CLIENTE DESESTRUCTURADO ===")

                val (nombre, correo, telefono) = clienteDemo

                println("Nombre: $nombre")
                println("Correo: $correo")
                println("Teléfono: $telefono")

                // 3. Anotaciones y promociones

                println()
                println("=== 3. PRODUCTOS PROMOCIONABLES ===")

                for (producto in catalogo) {
                    ReflectionUtils.mostrarInformacionPromocion(producto)
                    println()
                }

                // 4. Combinación de pedidos

                println()
                println("=== 4. COMBINACIÓN DE PEDIDOS ===")

                val pedidoA = Pedido(
                    idPedido = 1001,
                    cliente = clienteDemo,
                    productos = listOf(catalogo[0]),
                    cantidades = listOf(2)
                )

                val pedidoB = Pedido(
                    idPedido = 1002,
                    cliente = clienteDemo,
                    productos = listOf(catalogo[1]),
                    cantidades = listOf(1)
                )

                pedidoA.fechaPedido = LocalDate.now()
                pedidoB.fechaPedido = pedidoA.fechaPedido

                pedidoA.fechaInicioPromocion = fechaInicioPromocion
                pedidoA.fechaFinPromocion = fechaFinPromocion

                pedidoB.fechaInicioPromocion = fechaInicioPromocion
                pedidoB.fechaFinPromocion = fechaFinPromocion

                val pedidoCombinado = pedidoA + pedidoB

                println("Pedido A: ${pedidoA.idPedido}")
                println("Pedido B: ${pedidoB.idPedido}")
                println("Resultado de combinar ambos pedidos:")

                println(pedidoCombinado.mostrarResumen())


                println()
                println("=== COMPARACIÓN CON PROMOCIÓN ACTIVA ===")

                val pedidoPromocionalA = Pedido(
                    idPedido = 2001,
                    cliente = clienteDemo,
                    productos = listOf(catalogo[0]),
                    cantidades = listOf(2)
                )

                val pedidoPromocionalB = Pedido(
                    idPedido = 2002,
                    cliente = clienteDemo,
                    productos = listOf(catalogo[1]),
                    cantidades = listOf(1)
                )

                val fechaDemostracion = fechaInicioPromocion

                for (pedidoDemo in listOf(
                    pedidoPromocionalA,
                    pedidoPromocionalB
                )) {
                    pedidoDemo.fechaPedido = fechaDemostracion
                    pedidoDemo.fechaInicioPromocion = fechaInicioPromocion
                    pedidoDemo.fechaFinPromocion = fechaFinPromocion
                }

                val pedidoConPromocion =
                    pedidoPromocionalA + pedidoPromocionalB

                println("Fecha promocional: $fechaDemostracion")
                println(pedidoConPromocion.mostrarResumen())


                // 5. Desestructuración de Pedido

                println()
                println("=== 5. PEDIDO DESESTRUCTURADO ===")

                val (_, clientePedido, productosPedido, _, _, total) =
                    pedidoCombinado

                println("Cliente: ${clientePedido.nombre}")

                println(
                    "Productos: ${
                        productosPedido.joinToString { it.nombre }
                    }"
                )

                println("Total: $$total")

                // 6. Reflection de clases

                println()
                println("=== 6. REFLECTION ===")

                ReflectionUtils.mostrarEstructuraClase(clienteDemo)
                ReflectionUtils.mostrarEstructuraClase(pedidoCombinado)

                println()
                println("=== FIN DE LA DEMOSTRACIÓN ===")
            }


            "0" -> {
                println("Gracias por utilizar Pizzería Kotlin.")
                break
            }

            else -> {
                println("Opción inválida. Intente nuevamente.")
            }
        }
    }
}
