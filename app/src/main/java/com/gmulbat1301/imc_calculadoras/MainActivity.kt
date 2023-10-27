package com.gmulbat1301.imc_calculadoras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private lateinit var btnList : ArrayList<ImageButton>
    private lateinit var btnSalir : Button

    // Hacemos un array de class.java para hacer el acceso a las activities mucho mas facil y mas modular
    private val activityClasses = listOf(
        CalculadoraGuille::class.java,
        CalculadoraAlvaro::class.java,
        CalculadoraIMC::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Llamamos a las dos funciones que inicializan las variables y los eventos
        initComponents()
        initListeners()
    }

    // Inicializamos las variables de los componentes que vamos a usar
    private fun initComponents(){
        btnSalir = findViewById(R.id.Salir)
        btnList = ArrayList()
        btnList.add(findViewById(R.id.CalculadoraGuille))
        btnList.add(findViewById(R.id.CalculadoraAlvaro))
        btnList.add(findViewById(R.id.CalculadoraIMC))
    }

    // Inicializamoslos eventos y las funciones de los componentes
    private fun initListeners(){
        for (i in 0..<btnList.count()){
            btnList[i].setOnClickListener{ btnClicked(i) }
        }

        btnSalir.setOnClickListener { System.exit(0) }
    }


    /**
     * Usamos el array activityClasses para acceder a las diferentes activities
     * reccorriendolo con un bucle y un iterador
     */
    private fun btnClicked(i: Int) {
        if (i >= 0 && i < activityClasses.size) {
            val accesoCalculadora = Intent(this, activityClasses[i])
            startActivity(accesoCalculadora)
        }
    }
}



