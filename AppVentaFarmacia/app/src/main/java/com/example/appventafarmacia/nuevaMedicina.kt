package com.example.appventafarmacia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nueva_medicina.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class nuevaMedicina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_medicina)

        var idmedicina: Int?= null

        if (intent.hasExtra("medicina")){

            val  medicina = intent.extras?.getSerializable("medicina")as medicina

            nombre_et.setText(medicina.nombre)
            precio_et.setText(medicina.precio.toString())
            receta_et.setText(medicina.receta)
            idmedicina = medicina.idmedicina
        }
        val database = AppDB.getDatabase(this)

        save_btn.setOnClickListener {
            val nombre = nombre_et.text.toString()
            val  precio= precio_et.text.toString().toDouble()
            val  receta = receta_et.text.toString()

            val Medicina = medicina (nombre, precio, receta, R.drawable.ic_launcher_background)

            if (idmedicina !=null){
                CoroutineScope(Dispatchers.IO).launch {
                    Medicina.idmedicina=idmedicina

                    database.medicinas().update(Medicina)

                    this@nuevaMedicina.finish()

                }

            }else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.medicinas().insertAll(Medicina)

                    this@nuevaMedicina.finish()
                }

            }
        }
    }
}