package anotaciones

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Promocionable(
    val porcentaje: Int = 15,
    val descripcion: String = "Producto con promoción"
)
