package com.gmulbat1301.imc_calculadoras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CalculadoraGuille : AppCompatActivity() {

    // Declaración de variables:
    private lateinit var inputTextView: TextView
    private lateinit var resultTextView: TextView

    private lateinit var ceButton: Button
    private lateinit var equalsButton: Button
    private lateinit var decimalButton: Button
    private lateinit var backspaceButton: Button

    private lateinit var numberButtons: Array<Button>
    private lateinit var operationButtons: Array<Button>
    private lateinit var operationSymbols: Array<String>

    private val calculo = CalculoGuille()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_guille)

        initComponents()
        initListeners()
    }

    private fun initComponents(){
        inputTextView = findViewById(R.id.inputTextView)
        resultTextView = findViewById(R.id.resultTextView)
        ceButton = findViewById(R.id.buttonCE)
        equalsButton = findViewById(R.id.buttonEquals)
        decimalButton = findViewById(R.id.buttonDecimal)
        backspaceButton = findViewById(R.id.buttonBackspace)


        // Manejar clic en números del 0 al 9
        numberButtons = arrayOf(
            findViewById<Button>(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        // Manejar clic en botones de operación (+, -, x, /)
        operationButtons = arrayOf(
            findViewById<Button>(R.id.buttonPlus),
            findViewById(R.id.buttonMinus),
            findViewById(R.id.buttonMultiply),
            findViewById(R.id.buttonDivide)
        )

        operationSymbols = arrayOf("+", "-", "x", "/")

    }

    @SuppressLint("SetTextI18n")
    private fun initListeners(){
        for (i in 0..9) {
            numberButtons[i].setOnClickListener { onNumberClick(i) }
        }

        for (i in operationButtons.indices) {
            operationButtons[i].setOnClickListener { onOperationClick(operationSymbols[i]) }
        }

        // Manejar clic en botón CE (borrar)
        ceButton.setOnClickListener { onClearClick() }

        // Manejar clic en botón igual (=)
        equalsButton.setOnClickListener { onEqualsClick() }

        // Manejar clic en botón decimal (.)
        decimalButton.setOnClickListener {
            val currentText = inputTextView.text.toString()
            // Verifica si el punto decimal ya está presente
            if (!currentText.contains(".")) {
                // Si no hay un punto decimal, agrega uno al número actual
                inputTextView.text = "$currentText."
            }
        }

        // Manejar clic en botón backspace (<)
        backspaceButton.setOnClickListener {
            calculo.deleteLastCharacter()
            updateResult()
        }
    }


    // Funciones de control de clicks de botones
    private fun onNumberClick(number: Int) {
        calculo.setNumClicked(number)
        updateResult()
    }

    private fun onOperationClick(operation: String) {
        calculo.setOperation(operation)
        updateResult()
    }

    private fun onClearClick() {
        calculo.clear()
        updateResult()
    }

    private fun onEqualsClick() {
        if (calculo.num1 != 0f && calculo.num2 != 0f && calculo.operacion.isNotEmpty()) {
            calculo.calculate()
            updateResult()
        } else {
            // Mostrar mensaje de error si no se pueden realizar cálculos
            Toast.makeText(this, "Debe introducir 2 números y una operación para mostrar un resultado.", Toast.LENGTH_SHORT).show()
        }
    }

    // Control de lo que se muestra por pantalla
    private fun updateResult() {
        inputTextView.text = if (calculo.operacion.isEmpty()) {
            // Si no hay operación, muestra num1
            calculo.num1.toString()
        } else {
            // Si hay operación, muestra num2 si es diferente de cero, de lo contrario, muestra la operación
            if (calculo.num2 != 0f) {
                calculo.num2.toString()
            } else {
                calculo.operacion
            }
        }

        resultTextView.text = calculo.num1.toString()
        if (calculo.operacion.isNotEmpty()) {
            resultTextView.append(" ${calculo.operacion} ")
        }
        if (calculo.num2 != 0f) {
            resultTextView.append(calculo.num2.toString())
        }
    }
}
