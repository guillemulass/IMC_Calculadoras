package com.gmulbat1301.imc_calculadoras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

/**
 * @author: Álvaro Castilla
 * @author: Guillermo Mulas
 */

/**
 * Clase principal de la CalculadoraIMC
 */
class CalculadoraIMC : AppCompatActivity() {

    //lateinits de variables
    private lateinit var btnSubsPeso : FloatingActionButton
    private lateinit var btnAddPeso : FloatingActionButton
    private lateinit var txtPeso : TextView
    private lateinit var btnSubsEdad : FloatingActionButton
    private lateinit var btnAddEdad : FloatingActionButton
    private lateinit var txtEdad : TextView

    private lateinit var slider : RangeSlider
    private lateinit var txtCm : TextView
    private lateinit var btnCalc : Button

    private lateinit var btnMujer : Button
    private lateinit var btnHombre : Button
    private lateinit var sexo : String
    private lateinit var IMCaMostrar : String
    private lateinit var btnSalir : Button


    //inicializacion de variables de texto de edad y peso
    private var textoPeso = 60
    private var textoEdad = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_imc)

        initComponents()
        initListeners()
    }

    /**
     * Función que inicializa los componentes.
     */
    private fun initComponents(){
        slider = findViewById(R.id.slider)
        slider.valueFrom=120f
        slider.valueTo=200f
        slider.values = listOf(120f,200f)
        slider.stepSize = 0.5f

        txtCm = findViewById(R.id.stringcm)
        txtPeso = findViewById(R.id.stringpeso)
        txtEdad = findViewById(R.id.stringedad)

        btnCalc= findViewById(R.id.btn_Calc)
        btnMujer = findViewById(R.id.btn_Mujer)
        btnHombre = findViewById(R.id.btn_Hombre)
        //
        btnSalir = findViewById(R.id.btn_Salir)
        //
        sexo = ""


        btnAddPeso = (findViewById(R.id.sumar_peso))
        btnSubsPeso = (findViewById(R.id.restar_peso))
        btnAddEdad = (findViewById(R.id.sumar_edad))
        btnSubsEdad = (findViewById(R.id.restar_edad))
    }

    /**
     * Función que contiene los listeners necesarios.
     */
    private fun initListeners( ){
        btnCalc.setOnClickListener {
            btnCalc()
        }
        slider.addOnChangeListener { slider, _, _ ->
            val valueText = String.format("%.1f - %.1f cm", slider.values[0], slider.values[1])
            txtCm.text = valueText
        }

        btnAddPeso.setOnClickListener{ updatetxtPeso("+") }
        btnSubsPeso.setOnClickListener{ updatetxtPeso("-") }
        btnAddEdad.setOnClickListener{ updatetxtEdad("+") }
        btnSubsEdad.setOnClickListener{ updatetxtEdad("-") }

        btnMujer.setOnClickListener {
            sexo = "Mujer"

        }
        btnHombre.setOnClickListener { sexo = "Hombre" }
        btnSalir.setOnClickListener { finish() }
    }

    /**
     * Función diseñada para actualizar el texto de peso con los botones + y -
     * @param op -> recupera el operador pulsado (+ o -) para subir y bajar el txtPeso
     */
    private fun updatetxtPeso(op : String){
        when (op){
            "+"-> {
                    textoPeso += 1
            }
            "-"-> {
                if (textoPeso>0){
                    textoPeso -= 1
                }
            }
        }
        txtPeso.text = textoPeso.toString()
    }
    /**
     * Función diseñada para actualizar el texto de la edad con los botones + y -
     * @param op -> recupera el operador pulsado (+ o -) para subir y bajar el txtEdad
     */
    private fun updatetxtEdad(op : String){
        when (op){
            "+"-> {
                    textoEdad += 1
            }
            "-"-> {
                if (textoEdad>0) {
                    textoEdad -= 1
                }
            }
        }
        txtEdad.text = textoEdad.toString()
    }

    /**
     * Función diseñada para calcular el valor de IMC. Abre nuevo activity y muestra resultados.
     */
    private fun btnCalc() {
        //recupera altura media
        val alturamedia = ((slider.values[0]/100) + (slider.values[1] /100))/2
        //hace operación IMC
        val IMC = (textoPeso / (alturamedia*alturamedia))
        //establece formato
        val df = DecimalFormat("#.##")
        IMCaMostrar = df.format(IMC)
        //intent de nuevo activity
        val accesoResultado = Intent(this, IMCResultado::class.java)
        //gestiona error si no se establece sexo
        if (sexo == "") {
            Toast.makeText(this,"Debe introducir un sexo",Toast.LENGTH_LONG).show()
            }
        //si se ha establecido un sexo se abre nuevo activity
        else {
            accesoResultado.putExtra("IMCaMostrar",IMCaMostrar)
            accesoResultado.putExtra("sexo",sexo)
            startActivity(accesoResultado)
        }

    }
}

