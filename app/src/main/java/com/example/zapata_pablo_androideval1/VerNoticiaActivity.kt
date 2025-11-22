package com.example.zapata_pablo_androideval1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerNoticiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_noticia)
        findViewById<TextView>(R.id.tvTitulo).text = intent.getStringExtra("titulo") ?: ""
        findViewById<TextView>(R.id.tvResumen).text = intent.getStringExtra("resumen") ?: ""
        findViewById<TextView>(R.id.tvContenido).text = intent.getStringExtra("contenido") ?: ""
        findViewById<TextView>(R.id.tvAutor).text = "Autor: " + (intent.getStringExtra("autor") ?: "")
        findViewById<TextView>(R.id.tvFecha).text = "Fecha: " + (intent.getStringExtra("fecha") ?: "")
    }
}
