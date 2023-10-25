package com.gmulbat1301.imc_calculadoras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class CalculadoraIMC : AppCompatActivity() {

    lateinit var slider : RangeSlider
    lateinit var txtCm : TextView
    lateinit var btnCalc : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_imc)

        initComponents()
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

    fun updatetxtCm() {
        slider.addOnChangeListener { slider,value,fromUser ->
            val valueText = String.format("%.1f - %.1f cm", slider.values[0], slider.values[1],)
            txtCm.text = valueText
        }
    }

    fun btnCalc() {
        btnCalc.setOnClickListener { println() }
    }
}

