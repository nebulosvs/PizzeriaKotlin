package modelo

class Repartidor(
    override val nombre: String,
    override val contacto: String,
    val zonaReparto: String,
    var disponible: Boolean = true
) : Usuario(nombre, contacto) {

    override fun mostrarInformacion(): String {
        return """
            Nombre: $nombre
            Teléfono: $contacto
            Zona de reparto: $zonaReparto
            Disponible: ${if (disponible) "Sí" else "No"}
        """.trimIndent()
    }

    fun entregarPedido(idPedido: Int): String {
        disponible = false

        return "El repartidor $nombre ha iniciado la entrega del pedido N° $idPedido."
    }
}
