package modelo

open class Producto(
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val ingredientes: Map<String, Int> = emptyMap()
) {

    init {
        require(nombre.isNotBlank()) {
            "El nombre del producto no puede estar vacío."
        }

        require(precio.isFinite() && precio > 0) {
            "El precio debe ser mayor que cero."
        }

        require(ingredientes.values.all { it > 0 }) {
            "Las cantidades de ingredientes deben ser positivas."
        }
    }

    open fun detallarProducto(): String {
        return """
            Producto: $nombre
            Categoría: $categoria
            Precio: $$precio
            Ingredientes: $ingredientes
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true

        if (other !is Producto) return false

        return nombre == other.nombre &&
                categoria == other.categoria
    }

    override fun hashCode(): Int {

        var resultado = nombre.hashCode()
        resultado = 31 * resultado + categoria.hashCode()

        return resultado
    }
}
