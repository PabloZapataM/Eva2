package com.example.zapata_pablo_androideval1

import android.content.Intent 
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_noticia)

        // Datos recibidos desde el adapter
        val nombre = intent.getStringExtra("nombre") ?: ""
        val fecha = intent.getStringExtra("fecha") ?: ""
        val motivo = intent.getStringExtra("motivo") ?: ""

        // Asignar a los TextView del XML
        findViewById<TextView>(R.id.tvTitulo).text = motivo
        findViewById<TextView>(R.id.tvContenido).text = motivo
        findViewById<TextView>(R.id.tvAutor).text = "Autor: $nombre"
        findViewById<TextView>(R.id.tvFecha).text = "Fecha: $fecha"

        val btnVolver = findViewById<Button>(R.id.btnVolverInicio)

        btnVolver.setOnClickListener {
            finish() // Solo volver atrás, NO crear nueva activity
        }
    }
}
