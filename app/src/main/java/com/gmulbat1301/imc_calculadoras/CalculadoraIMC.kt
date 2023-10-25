package com.gmulbat1301.imc_calculadoras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

//el boton se ve to raro, hay q hacer lo d los kilos y la edad, hay q poner boton salir,terminar calcular,etc.
class CalculadoraIMC : AppCompatActivity() {


    private lateinit var btnSubsPeso : FloatingActionButton
    private lateinit var btnAddPeso : FloatingActionButton
    private lateinit var txtPeso : TextView
    private lateinit var btnSubsEdad : FloatingActionButton
    private lateinit var btnAddEdad : FloatingActionButton
    private lateinit var txtEdad : TextView

    private lateinit var slider : RangeSlider
    private lateinit var txtCm : TextView
    private lateinit var btnCalc : Button

    private var textoPeso = 60
    private var textoEdad = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_imc)

        initComponents()
        initListeners()
    }
    private fun initComponents(){
        slider = findViewById(R.id.slider)
        slider.valueFrom=120f
        slider.valueTo=200f
        slider.values = listOf(120f,200f)

        txtCm = findViewById(R.id.stringcm)
        txtPeso = findViewById(R.id.stringpeso)
        txtEdad = findViewById(R.id.stringedad)

        btnCalc= findViewById(R.id.btn_Calc)


        btnAddPeso = (findViewById(R.id.sumar_peso))
        btnSubsPeso = (findViewById(R.id.restar_peso))
        btnAddEdad = (findViewById(R.id.sumar_edad))
        btnSubsEdad = (findViewById(R.id.restar_edad))
    }

    private fun initListeners( ){
        btnCalc.setOnClickListener {
            btnCalc()
        }
        updatetxtCm()

        btnAddPeso.setOnClickListener{ updatetxtPeso("+") }
        btnSubsPeso.setOnClickListener{ updatetxtPeso("-") }
        btnAddEdad.setOnClickListener{ updatetxtEdad("+") }
        btnSubsEdad.setOnClickListener{ updatetxtEdad("-") }

    }

    private fun updatetxtCm() {
        slider.addOnChangeListener { slider,value,fromUser ->
            val valueText = String.format("%.1f - %.1f cm", slider.values[0], slider.values[1])
            txtCm.text = valueText
        }
    }

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

    fun btnCalc() {
        val alturamedia = ((slider.values[0]/100) + (slider.values[1] /100))/2
        val IMC = (textoPeso / (alturamedia*alturamedia))
        val df = DecimalFormat("#.##")
        val IMCaMostrar = df.format(IMC)
        Toast.makeText(this, IMCaMostrar,Toast.LENGTH_LONG).show()
    }
}

