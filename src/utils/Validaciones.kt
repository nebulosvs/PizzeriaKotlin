package utils

object Validaciones {

    fun leerTexto(mensaje: String): String {
        while (true) {
            print(mensaje)

            val texto = readlnOrNull()?.trim().orEmpty()

            if (texto.isNotBlank()) {
                return texto
            }

            println("Error: el campo no puede estar vacío.")
        }
    }

    fun leerPrecio(mensaje: String): Double {
        while (true) {
            print(mensaje)

            try {
                val precio = readlnOrNull()?.trim()?.toDoubleOrNull()

                if (precio != null && precio.isFinite() && precio > 0) {
                    return precio
                }

                println("Error: ingresa un precio válido mayor que cero.")

            } catch (e: Exception) {
                println("Error al procesar el precio: ${e.message}")
            }
        }
    }

    fun leerCantidad(mensaje: String): Int {
        while (true) {
            print(mensaje)

            val cantidad = readlnOrNull()?.trim()?.toIntOrNull()

            if (cantidad != null && cantidad in 1..100) {
                return cantidad
            }

            println("Error: ingresa una cantidad entre 1 y 100.")
        }
    }


    fun leerTelefono(mensaje: String): String {

        while (true) {

            print(mensaje)

            val telefono = readlnOrNull()?.trim().orEmpty()

            try {

                require(
                    telefono.matches(
                        Regex("""^\+?[\d\s()-]+$""")
                    )
                ) {
                    "El teléfono contiene caracteres no permitidos."
                }

                val telefonoNormalizado =
                    normalizarTelefono(telefono)

                if (validarTelefonoNormalizado(telefonoNormalizado)) {

                    println(
                        "Teléfono normalizado: $telefonoNormalizado"
                    )

                    return telefonoNormalizado
                }

                println("Error: formato telefónico inválido.")

            } catch (e: IllegalArgumentException) {

                println("Error: ${e.message}")
                println("Intenta nuevamente.")
            }
        }
    }


    fun leerCorreo(mensaje: String): String {
        while (true) {
            print(mensaje)

            val correo = readlnOrNull()?.trim()

            if (correo != null &&
                correo.matches(
                    Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""")
                )
            ) {
                return correo
            }

            println("Error: ingresa un correo electrónico válido.")
        }
    }

    fun validarInventario(cantidad: Int) {
        try {
            require(cantidad >= 0) {
                "El inventario no puede ser negativo."
            }

            println("Inventario válido: $cantidad unidades.")

        } catch (e: IllegalArgumentException) {
            println("Error de inventario: ${e.message}")
        }
    }

    fun mostrarObservacion(observacion: String?) {
        val texto = observacion?.trim()?.takeIf { it.isNotEmpty() }

        val mensaje = texto ?: "Sin observaciones adicionales."

        println("Observación: $mensaje")

        texto?.let {
            println("Notificación: el pedido incluye instrucciones especiales.")
        }
    }


    fun leerIdPedido(mensaje: String): Int {
        while (true) {
            print(mensaje)

            val id = readlnOrNull()?.trim()?.toIntOrNull()

            if (id != null && id > 0) {
                return id
            }

            println("Error: ingresa un ID de pedido válido y positivo.")
        }
    }



    fun normalizarTelefono(telefono: String): String {

        val digitos = telefono.filter { it.isDigit() }

        // Número móvil chileno: 9 dígitos nacionales.
        // También acepta el código de país +56.
        val numeroChileno = when {
            digitos.length == 9 && digitos.startsWith("9") ->
                digitos

            digitos.length == 11 &&
                    digitos.startsWith("56") &&
                    digitos[2] == '9' ->
                digitos.substring(2)

            else -> null
        }

        if (numeroChileno != null) {
            return "+56 (${numeroChileno.substring(0, 3)}) " +
                    "${numeroChileno.substring(3, 6)}-" +
                    numeroChileno.substring(6, 9)
        }

        // Formato internacional solicitado en la pauta:
        // +XX (XXX) XXX-XXXX
        require(digitos.length == 12) {
            "Ingresa un móvil chileno válido o un número " +
                    "internacional de 12 dígitos."
        }

        val codigoPais = digitos.substring(0, 2)
        val primeraParte = digitos.substring(2, 5)
        val segundaParte = digitos.substring(5, 8)
        val terceraParte = digitos.substring(8, 12)

        return "+$codigoPais ($primeraParte) " +
                "$segundaParte-$terceraParte"
    }




    fun validarTelefonoNormalizado(telefono: String): Boolean {

        val formatoInternacional =
            Regex("""^\+\d{2} \(\d{3}\) \d{3}-\d{4}$""")

        val formatoChileno =
            Regex("""^\+56 \(9\d{2}\) \d{3}-\d{3}$""")

        return formatoInternacional.matches(telefono) ||
                formatoChileno.matches(telefono)
    }



}
