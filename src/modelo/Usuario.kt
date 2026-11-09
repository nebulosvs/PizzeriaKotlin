package modelo

open class Usuario(
    open val nombre: String,
    open val contacto: String
) {

    open fun mostrarInformacion(): String {
        return "Nombre: $nombre\nContacto: $contacto"
    }
}
