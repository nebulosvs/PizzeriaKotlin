# 🍕 PizzeriaKotlin

**Sistema de Gestión de Pedidos para una Pizzería**

Proyecto desarrollado en **Kotlin** como aplicación de consola para gestionar pedidos, clientes, productos, inventario y repartidores, aplicando los principios de programación orientada a objetos y funcionalidades avanzadas del lenguaje.

**Asignatura:** PMY2201  
**Evaluación:** Semana 3 – Creando soluciones con Kotlin  
**Lenguaje:** Kotlin  
**Entorno de desarrollo:** IntelliJ IDEA  
**JDK:** Java 21

---

## 1. Descripción del proyecto

PizzeriaKotlin es una aplicación de consola que simula el funcionamiento de una pizzería, permitiendo registrar clientes, seleccionar productos, controlar la disponibilidad de ingredientes, calcular precios y descuentos, administrar pedidos y asignar repartidores.

El sistema incorpora validaciones de entrada, manejo de estados, promociones por fechas y mecanismos para evitar registros duplicados.

Además, implementa características avanzadas de Kotlin, como sobrecarga de operadores, desestructuración de objetos, anotaciones personalizadas, reflection y redefinición de los métodos `equals()` y `hashCode()`.

## 2. Funcionalidades principales

### Gestión de clientes

- Registro de clientes con nombre, correo electrónico, teléfono y dirección.
- Validación del formato del correo electrónico mediante expresiones regulares.
- Normalización y validación de números telefónicos.
- Detección de clientes previamente registrados.
- Reutilización de clientes existentes para evitar duplicados.

### Gestión de productos y pedidos

- Catálogo de pizzas con precios e ingredientes definidos.
- Selección de productos y cantidades.
- Validación de cantidades permitidas entre 1 y 100.
- Cálculo automático de subtotal, descuentos, impuestos y total.
- Registro de pedidos con identificadores únicos.
- Consulta del historial y estado de los pedidos.

### Gestión de inventario

- Registro de ingredientes disponibles.
- Verificación del stock antes de confirmar un pedido.
- Descuento de ingredientes al registrar pedidos válidos.
- Rechazo de pedidos cuando los ingredientes son insuficientes.
- Devolución de ingredientes al cancelar pedidos que todavía están en preparación.

### Gestión de repartidores

- Registro de repartidores con zonas de cobertura.
- Asignación de repartidores disponibles según la zona solicitada.
- Actualización de los estados de los pedidos.
- Liberación del repartidor al completar una entrega.

### Estados de pedidos

El sistema contempla los siguientes estados:

- En preparación.
- Listo.
- En camino.
- Entregado.
- Cancelado.
- Rechazado.

## 3. Funcionalidades avanzadas de Kotlin

### 3.1. Expresiones regulares y rangos

La clase `Validaciones` incorpora mecanismos para verificar la información ingresada por el usuario.

Se implementan:

- Expresiones regulares para validar correos electrónicos.
- Normalización y validación de teléfonos.
- Rangos para restringir cantidades entre 1 y 100.
- Rangos de fechas para determinar la vigencia de las promociones.
- Solicitud de nuevos datos cuando una entrada no es válida.

### 3.2. Anotaciones personalizadas

Se implementa la anotación `@Promocionable`, que permite identificar productos elegibles para descuentos.

La anotación contiene información sobre el porcentaje y la descripción de la promoción.

Se utiliza en la clase `Pizza` y puede consultarse durante el procesamiento de pedidos.

### 3.3. Reflection

La utilidad `ReflectionUtils` permite inspeccionar información de las clases durante la ejecución.

Sus funcionalidades incluyen:

- Identificación de productos con la anotación `@Promocionable`.
- Obtención del porcentaje de descuento definido en la anotación.
- Consulta de propiedades y métodos de las clases.
- Demostración de reflection sobre objetos de las clases `Cliente` y `Pedido`.

### 3.4. Sobrecarga de operadores

Se implementa la sobrecarga del operador `+` en la clase `Pedido`.

Este operador permite combinar dos pedidos compatibles pertenecientes al mismo cliente, agrupando sus productos y cantidades y recalculando los valores correspondientes.

También se redefine `equals()` en la clase `Producto`, permitiendo comparar productos según su nombre y categoría mediante el operador `==`.

### 3.5. Desestructuración de objetos

Las clases `Cliente` y `Pedido` incorporan funciones `componentN()` para permitir la desestructuración de sus propiedades.

En `Cliente` se pueden obtener:

- Nombre.
- Correo electrónico.
- Teléfono.
- Dirección.

En `Pedido` se pueden obtener:

- Identificador.
- Cliente.
- Productos.
- Cantidades.
- Estado.
- Total calculado.

### 3.6. Igualdad y prevención de duplicados

Se redefinen `equals()` y `hashCode()` en las clases `Cliente` y `Producto`.

- Los clientes se identifican mediante su correo electrónico normalizado.
- Los productos se comparan por nombre y categoría.
- Se utilizan colecciones de tipo `Set` para comprobar la unicidad.
- Durante el registro se evita incorporar nuevamente clientes existentes.
- El catálogo se valida para detectar productos duplicados.

## 4. Promociones y cálculo de precios

El sistema contempla dos mecanismos de descuento:

| Condición | Descuento |
|---|---|
| Pedido con más de 3 unidades | 10 % sobre el subtotal |
| Productos promocionables dentro del período vigente | 15 % sobre los productos elegibles |

Cuando corresponde más de un descuento, se aplica el de mayor valor, sin acumularlos.

El cálculo del total considera:

**Total = Subtotal − Descuento + Impuesto**

El impuesto aplicado corresponde al 19 % sobre el monto posterior al descuento.

Los pedidos cancelados o rechazados presentan un total a pagar de cero.

## 5. Estructura del proyecto

```text
PizzeriaKotlin/
├── src/
│   ├── Main.kt
│   │
│   ├── anotaciones/
│   │   └── Promocionable.kt
│   │
│   ├── modelo/
│   │   ├── Usuario.kt
│   │   ├── Cliente.kt
│   │   ├── Repartidor.kt
│   │   ├── Producto.kt
│   │   ├── Pizza.kt
│   │   └── Pedido.kt
│   │
│   ├── servicio/
│   │   ├── InventarioService.kt
│   │   ├── PedidoService.kt
│   │   └── RepartidorService.kt
│   │
│   ├── utils/
│   │   ├── Validaciones.kt
│   │   ├── Promociones.kt
│   │   └── ReflectionUtils.kt
│   │
│   ├── PruebaUsuarios.kt
│   ├── PruebaPedidos.kt
│   ├── PruebaInventario.kt
│   ├── PruebaInventarioMultiple.kt
│   ├── PruebaRepartidores.kt
│   ├── PruebaHistorial.kt
│   ├── PruebaValidaciones.kt
│   ├── PruebaSemana3Validaciones.kt
│   ├── PruebaPromociones.kt
│   ├── PruebaPromocionesIntegradas.kt
│   ├── PruebaReflexion.kt
│   ├── PruebaOperadores.kt
│   ├── PruebaIgualdad.kt
│   ├── PruebaDesestructuracion.kt
│   └── PruebaDesestructuracionSemana3.kt
│
└── PizzeriaKotlin.iml
```

## 6. Requisitos para ejecutar

- IntelliJ IDEA con soporte para Kotlin.
- JDK 21.
- Compilador de Kotlin compatible con el proyecto.

La aplicación utiliza la biblioteca estándar de Kotlin y no requiere una base de datos ni servicios externos.

## 7. Instrucciones de ejecución

1. Descargar o descomprimir el proyecto.
2. Abrir IntelliJ IDEA.
3. Seleccionar **File → Open**.
4. Abrir la carpeta `PizzeriaKotlin`.
5. Verificar que el proyecto tenga configurado el JDK 21.
6. Esperar a que IntelliJ IDEA reconozca y configure el proyecto Kotlin.
7. Abrir el archivo `src/Main.kt`.
8. Ejecutar la función `main()` mediante **Run 'MainKt'**.
9. Interactuar con el menú utilizando la consola.

## 8. Menú principal

Al iniciar el programa, se presenta un menú con las siguientes opciones:

| Opción | Funcionalidad |
|---|---|
| 1 | Registrar nuevo pedido |
| 2 | Consultar inventario |
| 3 | Consultar pedidos del día |
| 4 | Consultar resumen de estados |
| 5 | Consultar repartidores |
| 6 | Cambiar estado de un pedido |
| 7 | Demostración de Kotlin avanzado |
| 0 | Salir del sistema |

La opción **7. Demostración de Kotlin avanzado** permite visualizar directamente las funcionalidades solicitadas para la evaluación de Semana 3.

Incluye:

- Comparación de clientes y productos.
- Verificación de unicidad mediante colecciones.
- Desestructuración de clientes.
- Identificación de productos promocionables.
- Combinación de pedidos con el operador `+`.
- Comparación de cálculos con y sin promoción vigente.
- Desestructuración de pedidos.
- Inspección de clases mediante reflection.

## 9. Pruebas realizadas

El proyecto incluye archivos independientes para verificar el funcionamiento de sus componentes.

| Archivo | Objetivo |
|---|---|
| `PruebaUsuarios.kt` | Verificar clases de usuarios y herencia |
| `PruebaPedidos.kt` | Comprobar creación y cálculos de pedidos |
| `PruebaInventario.kt` | Verificar operaciones de inventario |
| `PruebaInventarioMultiple.kt` | Comprobar descuentos de ingredientes para varios productos |
| `PruebaRepartidores.kt` | Verificar gestión y asignación de repartidores |
| `PruebaHistorial.kt` | Comprobar registro y consulta de pedidos |
| `PruebaValidaciones.kt` | Verificar validaciones de entrada |
| `PruebaSemana3Validaciones.kt` | Comprobar validaciones correspondientes a Semana 3 |
| `PruebaPromociones.kt` | Verificar reglas promocionales |
| `PruebaPromocionesIntegradas.kt` | Comprobar promociones aplicadas a pedidos |
| `PruebaReflexion.kt` | Verificar anotaciones y reflection |
| `PruebaOperadores.kt` | Comprobar sobrecarga de operadores |
| `PruebaIgualdad.kt` | Verificar igualdad y unicidad de objetos |
| `PruebaDesestructuracion.kt` | Comprobar desestructuración de clientes y pedidos |
| `PruebaDesestructuracionSemana3.kt` | Verificar desestructuración requerida para la evaluación |

Las pruebas pueden ejecutarse individualmente desde IntelliJ IDEA.

La ejecución principal del sistema se realiza desde `Main.kt`.

## 10. Consideraciones del sistema

- El inventario y los pedidos se administran en memoria durante la ejecución.
- Los datos no se almacenan de forma permanente al cerrar la aplicación.
- El sistema valida el inventario antes de confirmar pedidos.
- Las promociones se aplican únicamente cuando se cumplen sus condiciones.
- La asignación de repartidores depende de su disponibilidad y zona de cobertura.
- Las funcionalidades avanzadas pueden demostrarse sin registrar pedidos reales mediante la opción 7.

## 11. Conclusión

El desarrollo de PizzeriaKotlin permite integrar conceptos de programación orientada a objetos con funcionalidades avanzadas de Kotlin en un caso práctico de gestión de pedidos.

El proyecto aplica herencia, encapsulación, validaciones, colecciones, anotaciones, reflection, sobrecarga de operadores, desestructuración e igualdad de objetos.

La solución incorpora además controles de inventario, administración de pedidos y asignación de repartidores, permitiendo demostrar las funcionalidades implementadas tanto de manera integrada como mediante pruebas independientes.
