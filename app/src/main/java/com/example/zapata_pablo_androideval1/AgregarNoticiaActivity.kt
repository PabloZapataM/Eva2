package com.example.zapata_pablo_androideval1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AgregarNoticiaActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_noticia)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etResumen = findViewById<EditText>(R.id.etResumen)
        val etContenido = findViewById<EditText>(R.id.etContenido)
        val etAutor = findViewById<EditText>(R.id.etAutor)
        val etFecha = findViewById<EditText>(R.id.etFecha)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val noticia = Noticia(
                titulo = etTitulo.text.toString(),
                resumen = etResumen.text.toString(),
                contenido = etContenido.text.toString(),
                autor = etAutor.text.toString(),
                fecha = etFecha.text.toString()
            )
            db.collection("noticias").add(noticia)
                .addOnSuccessListener {
                    Toast.makeText(this, "Noticia agregada", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${'$'}{e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}
