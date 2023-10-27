package com.gmulbat1301.imc_calculadoras

import CalculoAlvaro
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


/**
 * @author Álvaro Castilla
 */

/**
 * Clase principal de la calculadora.
 * Maneja la interfaz de usuario y la lógica de esta.
 */
class CalculadoraAlvaro : AppCompatActivity() {
    // Declaración de variables:
    private lateinit var listaNumeros: MutableList<Button>
    private lateinit var listaOps: MutableList<Button>
    private lateinit var btnCalc: Button
    private lateinit var btnCE: Button
    private lateinit var buttonC: Button
    private lateinit var calc: CalculoAlvaro
    private lateinit var btn_salir: Button
    private lateinit var btn_punto: Button
    private lateinit var textopantalla: TextView
    private lateinit var pantallahistorial: TextView

    private lateinit var pantallaactual: String

    // variables para controlar el estado de la calculadora.

    private var numero1introducido: Boolean = false
    private var numero2introducido: Boolean = false
    private var reset: Boolean = false
    private var puntoAnadido = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_alvaro)


        /**
         * @class Calculo()
         * inicializa la clase Calculo en un objeto
         */
        calc = CalculoAlvaro()

        //inicializa una variable string del texto de la pantalla
        pantallaactual = ""

        // inicializa elementos de la interfaz
        pantallahistorial = findViewById(R.id.textohistorial)
        textopantalla = findViewById<Button>(R.id.pantallatexto)
        btnCalc = findViewById(R.id.buttonresult)
        btnCE = findViewById(R.id.buttonreset)
        buttonC = findViewById(R.id.buttonC)
        btn_salir = findViewById(R.id.exitbtn)
        btn_punto = findViewById(R.id.buttonpunto)

        // inicializan los botones de los números y las operaciones
        crearNumeros()
        crearOperadores()
        initListeners()


    }

    /**
     *  Inicializa listeners de botones (operador y número)
     */
    private fun initListeners() {
        //crea un setOnClickListener de cada botón que enlaza a la función botonClickado()
        for (i in 0 until listaNumeros.size) {
            listaNumeros[i].setOnClickListener { botonClikado(i) }
        }

        //crea un setOnClickListener de cada botón que enlaza a la función botonOperador()
        for (i in 0 until listaOps.size) {
            listaOps[i].setOnClickListener { botonOperador((listaOps[i].text).toString()) }
        }

        //setOnClickListener de los botones CE, C (borrar solo un dígito), SALIR Y CALCULAR EL RESULTADO
        btnCE.setOnClickListener { btnCE() }
        btnCalc.setOnClickListener { btnCalc() }
        btn_salir.setOnClickListener { finish() }
        buttonC.setOnClickListener { btnC() }
        btn_punto.setOnClickListener { anadirPunto() }
    }

    /**
     *   Función que ejecuta cada botón de cada numero
     */
    private fun crearNumeros() {
        //lista de todos los botones juntos
        listaNumeros = mutableListOf()
        //se agrega cada botón a la lista
        listaNumeros.add(findViewById(R.id.button0))
        listaNumeros.add(findViewById(R.id.button1))
        listaNumeros.add(findViewById(R.id.button2))
        listaNumeros.add(findViewById(R.id.button3))
        listaNumeros.add(findViewById(R.id.button4))
        listaNumeros.add(findViewById(R.id.button5))
        listaNumeros.add(findViewById(R.id.button6))
        listaNumeros.add(findViewById(R.id.button7))
        listaNumeros.add(findViewById(R.id.button8))
        listaNumeros.add(findViewById(R.id.button9))
    }

    /**
     *    Función que ejecuta cada botón de cada operador
     */
    private fun crearOperadores() {
        //lista de todos los botones juntos
        listaOps = mutableListOf()
        //se agrega cada botón a la lista
        listaOps.add(findViewById(R.id.buttonsumar))
        listaOps.add(findViewById(R.id.buttonrestar))
        listaOps.add(findViewById(R.id.buttonmult))
        listaOps.add(findViewById(R.id.buttondividir))
    }

    /**
     *    Función que ejecuta cada número clickado
     * @param num -> variable para reconocer cada botón clickado
     */
    private fun botonClikado(num: Int) {
        // significa que la operación anterior ya se ha realizado y se inicia una nueva operación.
        if (numero1introducido && numero2introducido) {
            pantallaactual = ""
            pantallaactual += num
            reset = true
            btnCE()
        } //se agrega a la pantalla el botón clickado
        else {
            pantallaactual += num
            textopantalla.text = pantallaactual
        }

    }

    /**
     * Función que se ejecuta cuando se clicka un operador
     *  @param operator -> variable que almacena operador pulsado
     */
    private fun botonOperador(operator: String) {
        // significa que la operación anterior ya se ha realizado y se inicia una operación con el resultado.
        if (numero1introducido && numero2introducido) {
            calc.num1 = calc.result
            calc.num2 = 0.0
            calc.operador = operator
            calc.result = 0.0
            numero1introducido = true
            numero2introducido = false

            pantallaactual = ""
            textopantalla.setText(pantallaactual)
        }
        //si no se ha hecho una operación previamente se guarda el operador y el primer número (num1)
        //se guarda el operador, se establece el paso 1 y se vacía la pantalla.
        else {
            if (pantallaactual.isNotBlank()) {
                calc.num1 = pantallaactual.toDouble()

            }
            calc.operador = operator
            numero1introducido = true
            pantallaactual = ""
            textopantalla.setText(pantallaactual)
        }
        puntoAnadido = false
    }

    /**
     * Función que se ejecuta cuando se clicka el botón CE
     */
    private fun btnCE() {
        calc.num1 = 0.0
        calc.num2 = 0.0
        calc.result = 0.0
        calc.operador = ""
        if (reset) {
            textopantalla.setText(pantallaactual)
            reset = false
        } else {
            textopantalla.setText("")
            pantallaactual = ""
            calc.historial = ""
            pantallahistorial.text = calc.historial
        }
        numero1introducido = false
        numero2introducido = false
        puntoAnadido = false
    }

    /**
     * Función que se ejecuta cuando se clicka el botón de calcular.
     */
    private fun btnCalc() {
        if (pantallaactual != "" && numero1introducido) {
            calc.num2 = pantallaactual.toDouble()
            numero2introducido = true
        }
        // genera el mensaje toast si no estan guardados los 2 números.
        if (!numero1introducido || !numero2introducido) {
            mensajeError("Introduzca 2 números y una operación para continuar")
        }
        //accede a la función de hacer operación de la clase Calculo()
        else {
            textopantalla.setText((calc.toDoOp()).toString())
            pantallahistorial.setText(calc.setNumClicked())
        }

    }

    /**
     * Función para mostrar un mensaje de error.
     * @param msj -> variable que contiene el mensaje de error a mostrar
     */
    private fun mensajeError(msj: String) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show()
    }

    /**
     * Función que se ejecuta cuando se clicka el botón C (borrado de 1 solo dígito).
     */
    @SuppressLint("SetTextI18n")
    fun btnC() {
        // si no hay un resultado en pantalla se borra el dígito
        if (calc.result == 0.0) {
            //controla cuando se intenta borrar y la pantalla es igual al operador pero no está vacía
            if (pantallaactual == calc.operador && pantallaactual.isNotBlank()) {
                pantallaactual = calc.num1.toString()
                calc.operador = ""
                numero1introducido = false
                puntoAnadido = false
                calc.num1 = 0.0
            }
            //controla cuando la pantalla esta vacía
            if (pantallaactual == "") {
                //controla cuando la pantalla esta vacía pero se ha introducido ya el primer numero
                if (numero1introducido) {
                    pantallaactual = calc.operador
                    textopantalla.text = pantallaactual
                    //se intenta borrar estando la pantalla vacía (genera error)
                } else {
                    mensajeError("No se puede borrar un número vacío.")
                }
                // la pantalla no está vacía. se borra último dígito.
            } else {
                pantallaactual = pantallaactual.substring(0, pantallaactual.length - 1)
                textopantalla.setText(pantallaactual)
            }
            // Si hay un resultado en pantalla no se puede borrar
        } else {
            mensajeError("No se puede borrar un resultado.")
            puntoAnadido = true
        }
    }

    /**
     * Función para añadir punto decimal.
     */
    private fun anadirPunto() {
        if (!puntoAnadido) {
            pantallaactual += "."
            textopantalla.text = pantallaactual
            puntoAnadido = true
        }
    }
}

