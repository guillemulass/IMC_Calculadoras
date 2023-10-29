package com.gmulbat1301.imc_calculadoras

class CalculoGuille {
    var num1: Float = 0f
    var num2: Float = 0f
    var operacion: String = ""
    private var resultado: Float = 0f

    fun setNumClicked(number: Int) {
        if (operacion.isEmpty()) {
            num1 = num1 * 10 + number
        } else {
            num2 = num2 * 10 + number
        }
    }

    fun setOperation(operation: String) {
        if (num1 != 0f && num2 != 0f) {
            calculate()
        }
        operacion = operation
    }

    fun calculate() {
        when (operacion) {
            "+" -> resultado = sumar(num1, num2)
            "-" -> resultado = restar(num1, num2)
            "x" -> resultado = multiplicar(num1, num2)
            "/" -> {
                if (num2 != 0f) {
                    resultado = dividir(num1, num2)
                }
            }
        }
        num1 = resultado
        num2 = 0f
        operacion = ""
    }

    fun deleteLastCharacter() {
        val temp : String
        if (operacion.isEmpty() && num1 != 0f) {
            temp = num1.toString().substring(0, num1.toString().length - 1)
            num1 = temp.toFloat()
        } else if (num2 != 0f) {
            temp = num2.toString().substring(0, num2.toString().length - 1)
            num2 = temp.toFloat()
        } else if (operacion.isNotEmpty()) {
            operacion = ""
        }

    }

    fun clear() {
        num1 = 0f
        num2 = 0f
        operacion = ""
        resultado = 0f
    }

    // Método para sumar
    private fun sumar(a: Float, b: Float): Float {
        return a + b
    }

    // Método para restar
    private fun restar(a: Float, b: Float): Float {
        return a - b
    }

    // Método para multiplicar
    private fun multiplicar(a: Float, b: Float): Float {
        return a * b
    }

    // Método para dividir
    private fun dividir(a: Float, b: Float): Float {
        return a / b
    }
}
