package com.gmulbat1301.imc_calculadoras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
class CalculadoraGuille : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_guille)
        val buttonOut = findViewById<Button>(R.id.interfaz_inicial_guille)
        buttonOut.setOnClickListener { finish() }

    }
}