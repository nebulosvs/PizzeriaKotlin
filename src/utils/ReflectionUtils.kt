package utils

import anotaciones.Promocionable
import modelo.Producto

object ReflectionUtils {

    fun esPromocionable(producto: Producto): Boolean {
        return producto.javaClass.isAnnotationPresent(
            Promocionable::class.java
        )
    }

    fun obtenerPorcentajePromocion(producto: Producto): Int {
        val anotacion = producto.javaClass.getAnnotation(
            Promocionable::class.java
        )

        return anotacion?.porcentaje ?: 0
    }

    fun mostrarInformacionPromocion(producto: Producto) {

        val anotacion = producto.javaClass.getAnnotation(
            Promocionable::class.java
        )

        if (anotacion != null) {
            println("Producto: ${producto.nombre}")
            println("Promocionable: Sí")
            println("Descuento: ${anotacion.porcentaje}%")
            println("Descripción: ${anotacion.descripcion}")
        } else {
            println("Producto: ${producto.nombre}")
            println("Promocionable: No")
        }
    }

    fun mostrarEstructuraClase(objeto: Any) {

        val clase = objeto.javaClass

        println()
        println("=== REFLEXIÓN: ${clase.simpleName} ===")

        println()
        println("Propiedades declaradas:")

        for (campo in clase.declaredFields) {
            if (!campo.isSynthetic) {
                println("- ${campo.name}: ${campo.type.simpleName}")
            }
        }

        println()
        println("Métodos declarados:")

        for (metodo in clase.declaredMethods) {
            if (!metodo.isSynthetic) {
                println("- ${metodo.name}()")
            }
        }

        println()
        println("Anotaciones:")

        val anotaciones = clase.declaredAnnotations

        if (anotaciones.isEmpty()) {
            println("- Sin anotaciones")
        } else {
            for (anotacion in anotaciones) {
                println("- ${anotacion.annotationClass.simpleName}")
            }
        }
    }
}
