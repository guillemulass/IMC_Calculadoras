package com.gmulbat1301.imc_calculadoras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnList : ArrayList<Button>

    lateinit var btnSalir : Button
    private val activityClasses = listOf(
        CalculadoraGuille::class.java,
        CalculadoraAlvaro::class.java,
        CalculadoraIMC::class.java
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()
    }

    private fun initComponents(){
        btnSalir = findViewById(R.id.Salir)
        btnList = ArrayList()
        btnList.add(findViewById(R.id.CalculadoraGuille))
        btnList.add(findViewById(R.id.CalculadoraAlvaro))
        btnList.add(findViewById(R.id.CalculadoraIMC))
    }


    private fun initListeners(){
        for (i in 0..<btnList.count()){
            btnList[i].setOnClickListener{ btnClicked(i) }
        }

        btnSalir.setOnClickListener { System.exit(0) }
    }


    // terminar la funcion
    private fun btnClicked(i: Int) {
        if (i >= 0 && i < activityClasses.size) {
            val accesoCalculadora = Intent(this, activityClasses[i])
            startActivity(accesoCalculadora)
        }
    }
}



