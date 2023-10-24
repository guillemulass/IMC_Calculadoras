package com.gmulbat1301.imc_calculadoras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class CalculadoraIMC : AppCompatActivity() {

    lateinit var slider : RangeSlider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_imc)

        initComponents()
    }
    fun initComponents(){
        slider = findViewById<RangeSlider>(R.id.slider)
        slider.valueFrom=120f
        slider.valueTo=200f
        slider.values = listOf(120f,200f)

    }
}

