package com.example.zapata_pablo_androideval1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_noticia)

        val titulo = intent.getStringExtra("titulo") ?: ""
        val contenido = intent.getStringExtra("contenido") ?: ""
        val autor = intent.getStringExtra("autor") ?: ""
        val fecha = intent.getStringExtra("fecha") ?: ""

        findViewById<TextView>(R.id.tvTitulo).text = titulo
        findViewById<TextView>(R.id.tvContenido).text = contenido
        findViewById<TextView>(R.id.tvAutor).text = "Autor: $autor"
        findViewById<TextView>(R.id.tvFecha).text = "Fecha: $fecha"

        findViewById<Button>(R.id.btnVolverInicio).setOnClickListener {
            finish()
        }
    }
}
