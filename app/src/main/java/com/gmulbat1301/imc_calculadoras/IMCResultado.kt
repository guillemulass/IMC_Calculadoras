package com.gmulbat1301.imc_calculadoras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class IMCResultado : AppCompatActivity() {
    var variableIMC : String? = ""
    var variablesexo : String? = ""

    lateinit var textviewIMC: TextView
    lateinit var textviewSexo: TextView
    lateinit var textviewExplicacion: TextView
    lateinit var btnSalir : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresultado)

        recuperarVariables()
        initComponents()
        crearExplicacion()
        btnSalir.setOnClickListener { finish() }

    }
    fun recuperarVariables() {
        val intent = intent
        if (intent.hasExtra("IMCaMostrar")) {
            variableIMC = intent.getStringExtra("IMCaMostrar")
        }
        if (intent.hasExtra("sexo")) {
            variablesexo = intent.getStringExtra("sexo")
        }
    }
    fun initComponents() {
        textviewIMC = findViewById(R.id.txtIMC)
        textviewSexo = findViewById(R.id.txtsexo)
        textviewExplicacion = findViewById(R.id.txtexplicacion)
        btnSalir = findViewById<Button?>(R.id.btn_SalirResultado)
    }


    @SuppressLint("SetTextI18n")
    fun crearExplicacion(){
        textviewIMC.text = "IMC: $variableIMC"
        textviewSexo.text = "Sexo: $variablesexo"
        if (variablesexo == "Hombre") {
            if (variableIMC?.toFloat()!! < 20f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un bajo peso.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 20f && variableIMC?.toFloat()!! <= 24.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un peso normal.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 25f && variableIMC?.toFloat()!! <= 29.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad leve.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 30f && variableIMC?.toFloat()!! <= 40f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad severa.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 40f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad muy severa.
            """.trimIndent()
            }
        }
        if (variablesexo == "Mujer") {
            if (variableIMC?.toFloat()!! < 20f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un bajo peso.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 20f && variableIMC?.toFloat()!! <= 23.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene un peso normal.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 24f && variableIMC?.toFloat()!! <= 28.9f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad leve.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 29f && variableIMC?.toFloat()!! <= 37f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad severa.
            """.trimIndent()
            }
            else if (variableIMC?.toFloat()!! > 37f) {
                textviewExplicacion.text = """
                Tu resultado de IMC es de $variableIMC.
                Usted tiene una obesidad muy severa.
            """.trimIndent()
            }
        }
    }
}