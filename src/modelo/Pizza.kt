package modelo

import anotaciones.Promocionable

@Promocionable(
    porcentaje = 15,
    descripcion = "Promoción especial en pizzas"
)
class Pizza(
    nombre: String,
    precio: Double,
    ingredientes: Map<String, Int>
) : Producto(
    nombre = nombre,
    precio = precio,
    categoria = "Pizza",
    ingredientes = ingredientes
) {

    override fun detallarProducto(): String {
        return buildString {
            appendLine("Pizza: $nombre")
            appendLine("Precio: $$precio")
            appendLine("Ingredientes necesarios:")

            for ((ingrediente, cantidad) in ingredientes) {
                appendLine("- $ingrediente: $cantidad")
            }
        }.trimEnd()
    }
}
