package com.example.appventafarmacia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_medicina.*
import kotlinx.android.synthetic.main.item_medicina.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class medicinaAct : AppCompatActivity() {

    private lateinit var database: AppDB
    private lateinit var medicina: medicina
    private lateinit var medicinaLiveData: LiveData<medicina>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicina)

        database = AppDB.getDatabase(this)
        val idmedicina = intent.getIntExtra("id", 0)
        medicinaLiveData = database.medicinas().get(idmedicina)

        medicinaLiveData.observe(this, Observer {
            medicina = it

            nombre_medicina.text = medicina.nombre
            precio_medicina.text = "$${medicina.precio}"
            receta_medicina.text = medicina.receta
            imagen.setImageResource(medicina.imagen)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.medicina_menu,menu)



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_item -> {
                val intent = Intent(this, nuevaMedicina::class.java)
                intent.putExtra("medicina", medicina)
                startActivity(intent)

            }
            R.id.delete_item -> {
                medicinaLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.medicinas().delete(medicina)
                    this@medicinaAct.finish()
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
