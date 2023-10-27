package com.gmulbat1301.imc_calculadoras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class IMCResultado : AppCompatActivity() {
    private var variableIMC : String? = ""
    private var variablesexo : String? = ""

    private lateinit var textviewIMC: TextView
    private lateinit var textviewSexo: TextView
    private lateinit var textviewExplicacion: TextView
    private lateinit var btnSalir : Button

    private lateinit var imagen : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresultado)

        recuperarVariables()
        initComponents()
        crearExplicacion()
        btnSalir.setOnClickListener { finish() }
    }

    // Para acceder a los resultados del calculo del IMC, que se calcula en la actividad CalculadoraIMC
    // llamamos a las variables con esta funcion
    private fun recuperarVariables() {
        val intent = intent
        if (intent.hasExtra("IMCaMostrar")) {
            variableIMC = intent.getStringExtra("IMCaMostrar")
        }
        if (intent.hasExtra("sexo")) {
            variablesexo = intent.getStringExtra("sexo")
        }
    }

    // Inicializamos las variables de los componentes que vamos a usar
    private fun initComponents() {
        textviewIMC = findViewById(R.id.txtIMC)
        textviewSexo = findViewById(R.id.txtsexo)
        textviewExplicacion = findViewById(R.id.txtexplicacion)
        btnSalir = findViewById(R.id.btn_SalirResultado)
        imagen = findViewById(R.id.imagen)
    }


    // Devolvemos los resultados del calculo de IMC con su explicacion correspondiente
    @SuppressLint("SetTextI18n")
    fun crearExplicacion(){
        // Estas variables no dependen de otras, por lo que se asignan directamente
        textviewIMC.text = "IMC: $variableIMC"
        textviewSexo.text = "Sexo: $variablesexo"
        // Primero diferenciamos entre Hombre y Mujer
        if (variablesexo == "Hombre") {
            // Ahora creamos diferentes explicaciones dependiendo de los rangos del IMC
            if (variableIMC?.toFloat()!! < 20f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un bajo peso.
               
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc1)
            }
            else if (variableIMC?.toFloat()!! > 20f && variableIMC?.toFloat()!! <= 24.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un peso normal.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc2)
            }
            else if (variableIMC?.toFloat()!! > 25f && variableIMC?.toFloat()!! <= 29.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad leve.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc3)
            }
            else if (variableIMC?.toFloat()!! > 30f && variableIMC?.toFloat()!! <= 40f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad severa.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc4)
            }
            else if (variableIMC?.toFloat()!! > 40f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad muy severa.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc5)
            }
        }

        if (variablesexo == "Mujer") {
            // Igual que en hombre, diferenciamos las explicacions segun el resultado de IMC y los rangos de este
            if (variableIMC?.toFloat()!! < 20f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un bajo peso.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc1)

            }
            else if (variableIMC?.toFloat()!! > 20f && variableIMC?.toFloat()!! <= 23.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un peso normal.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc2)

            }
            else if (variableIMC?.toFloat()!! > 24f && variableIMC?.toFloat()!! <= 28.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad leve.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc3)

            }
            else if (variableIMC?.toFloat()!! > 29f && variableIMC?.toFloat()!! <= 37f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad severa.
                               

            """.trimIndent()
                imagen.setImageResource(R.drawable.imc4)
            }
            else if (variableIMC?.toFloat()!! > 37f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad muy severa.
            """.trimIndent()
                imagen.setImageResource(R.drawable.imc5)

            }
        }
    }
}