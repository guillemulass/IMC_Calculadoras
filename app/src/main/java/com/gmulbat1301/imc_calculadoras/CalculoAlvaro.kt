
/**
 * @author Álvaro Castilla
 */

/**
 * Clase para el manejo del cálculo
 */
class CalculoAlvaro {

    //variables de numeros, resultado y operador.
    var num1 = 0.0
    var num2 = 0.0
    var result = 0.0
    var operador = ""

    //variable para almacenar la última operación ejecutada.
    lateinit var historial : String

    /**
     * Función que ejecuta diferentes funciones de operaciones dependiendo del operador pulsado.
     * @return result -> devuelve el resultado de la operación que se realice.
     */
    fun toDoOp():Double {
        when (operador) {
            "+" -> this.result = sumar()
            "-" -> this.result = restar()
            "*" -> this.result = multiplicar()
            "/" -> this.result = dividir()
        }
        return this.result
    }


    /**
     * Funciones para hacer las operaciones correspondientes
     */
    fun sumar():Double {
        return num1 + num2
    }
    fun restar():Double {
        return num1 - num2
    }
    fun multiplicar():Double {
        return num1 * num2
    }
    fun dividir():Double {
        return num1 / num2
    }

    /**
     * Función para guardar última operación en un string.
     * @return historial -> devuelve la última operación realizada.
     */
    fun setNumClicked():String {
        historial = "${num1} ${operador} ${num2} = ${(toDoOp())}"
        return historial
    }


}