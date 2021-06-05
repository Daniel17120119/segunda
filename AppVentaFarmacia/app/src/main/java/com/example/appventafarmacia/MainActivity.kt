package com.example.appventafarmacia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listamedicinas = emptyList<medicina>()

        val database = AppDB.getDatabase( this)

        database.medicinas().getAll().observe( this, Observer{
            listamedicinas= it

            val adapter = medicinaAdap(this,listamedicinas)

            lista.adapter = adapter
        })
        lista.setOnItemClickListener{ parent,view,position,id ->
            val intent = Intent(this,medicinaAct::class.java)
            intent.putExtra("id",listamedicinas[position].idmedicina)
            startActivity(intent)

        }
        floatingActionButton.setOnClickListener{
            val  intent = Intent(this,nuevaMedicina::class.java)
            startActivity(intent)
        }
    }
}