package com.gmulbat1301.imc_calculadoras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

//el boton se ve to raro, hay q hacer lo d los kilos y la edad, hay q poner boton salir,terminar calcular,etc.
class CalculadoraIMC : AppCompatActivity() {

    lateinit var slider : RangeSlider
    lateinit var txtCm : TextView
    lateinit var btnCalc : Button
    //var para probar btnCalc
    var peso : Double = 60.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_imc)

        initComponents()
        initListeners()
        updatetxtCm()
    }
    fun initComponents(){
        slider = findViewById<RangeSlider>(R.id.slider)
        slider.valueFrom=120f
        slider.valueTo=200f
        slider.values = listOf(120f,200f)

        txtCm = findViewById<TextView>(R.id.stringcm)
        btnCalc= findViewById(R.id.btn_Calc)
    }

    fun initListeners( ){
        btnCalc.setOnClickListener {
            btnCalc()
        }
    }

    fun updatetxtCm() {
        //esto g puede mete en un listener en vd
        slider.addOnChangeListener { slider,value,fromUser ->
            val valueText = String.format("%.1f - %.1f cm", slider.values[0], slider.values[1],)
            txtCm.text = valueText
        }
    }

    fun btnCalc() {
        val alturamedia = ((slider.values[0]/100) + (slider.values[1] /100))/2
        val IMC = (peso / (alturamedia*alturamedia))
        val df = DecimalFormat("#.##")
        var IMCaMostrar = df.format(IMC)
        Toast.makeText(this, IMCaMostrar,Toast.LENGTH_LONG).show()
    }
}

