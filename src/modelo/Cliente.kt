package modelo

class Cliente(
    override val nombre: String,
    override val contacto: String,
    val direccion: String,
    val correo: String
) : Usuario(nombre, contacto) {

    override fun mostrarInformacion(): String {
        return """
            Nombre: $nombre
            Teléfono: $contacto
            Dirección: $direccion
            Correo electrónico: $correo
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true

        if (other !is Cliente) return false

        return correo.trim().lowercase() ==
                other.correo.trim().lowercase()
    }

    override fun hashCode(): Int {
        return correo.trim().lowercase().hashCode()
    }

    operator fun component1(): String = nombre

    operator fun component2(): String = correo

    operator fun component3(): String = contacto

    operator fun component4(): String = direccion


}
