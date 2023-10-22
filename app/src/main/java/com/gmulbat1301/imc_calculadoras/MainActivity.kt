package com.gmulbat1301.imc_calculadoras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnList : ArrayList<Button>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()
        
    }

    private fun initComponents(){
        val btnSalir : Button = findViewById(R.id.Salir)

        btnList = ArrayList()
        btnList.add(findViewById(R.id.CalculadoraGuille))
        btnList.add(findViewById(R.id.CalculadoraAlvaro))
        btnList.add(findViewById(R.id.CalculadoraIMC))
        btnList.add(findViewById(R.id.Salir))
    }


    private fun initListeners(){
        for (i in 0..<btnList.count()){
            btnList[i].setOnClickListener{ btnClicked() }
        }
    }


    // terminar la funcion
    private fun btnClicked(){
            val accesoCalculadoraGuille : Intent = Intent(this, CalculadoraGuille::class.java)
            startActivity(accesoCalculadoraGuille)
    }
}



