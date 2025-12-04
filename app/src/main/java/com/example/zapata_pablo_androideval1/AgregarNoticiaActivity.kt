package com.example.zapata_pablo_androideval1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class AgregarNoticiaActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_noticia)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etFecha = findViewById<EditText>(R.id.etFecha)
        val etAutor = findViewById<EditText>(R.id.etAutor)
        val etResumen = findViewById<EditText>(R.id.etResumen)
        val etContenido = findViewById<EditText>(R.id.etContenido)
        val etImagenUrl = findViewById<EditText>(R.id.etImagenUrl)

        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnVolverInicio = findViewById<Button>(R.id.btnVolver)

        etFecha.setOnClickListener {
            mostrarDatePicker(etFecha)
        }

        btnGuardar.setOnClickListener {

            val noticia = hashMapOf(
                "titulo" to etTitulo.text.toString(),
                "fecha" to etFecha.text.toString(),
                "autor" to etAutor.text.toString(),
                "resumen" to etResumen.text.toString(),
                "contenido" to etContenido.text.toString(),
                "imagenUrl" to etImagenUrl.text.toString())

            db.collection("noticias").add(noticia)
                .addOnSuccessListener {
                    Toast.makeText(this, "Noticia guardada correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        btnVolverInicio.setOnClickListener {
            finish()
        }
    }

    private fun mostrarDatePicker(etFecha: EditText) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val selector = DatePickerDialog(this,
            { _, y, m, d ->
                val mm = m + 1
                etFecha.setText("%02d/%02d/%04d".format(d, mm, y))
            }, year, month, day)

        selector.show()
    }
}
