import modelo.Cliente
import modelo.Repartidor
import modelo.Usuario
import utils.Validaciones

fun main() {

    println("=== PRUEBA DE USUARIOS ===")

    val cliente = Cliente(
        nombre = "Pedro",
        contacto = Validaciones.normalizarTelefono("912345678"),
        direccion = "Av. Siempre Viva 123",
        correo = "pedro@gmail.com"
    )

    val repartidor = Repartidor(
        nombre = "Carlos Palacios",
        contacto = "987654321",
        zonaReparto = "Santiago Centro"
    )

    val usuarios: List<Usuario> = listOf(cliente, repartidor)

    for (usuario in usuarios) {
        println()
        println(usuario.mostrarInformacion())
    }

    println()
    println("=== ENTREGA DEL PEDIDO ===")
    println(repartidor.entregarPedido(1))
    println("Repartidor disponible: ${repartidor.disponible}")
}
