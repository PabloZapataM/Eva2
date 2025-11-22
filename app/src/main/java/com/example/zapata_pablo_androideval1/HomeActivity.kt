package com.example.zapata_pablo_androideval1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NewsAdapter
    private val db = FirebaseFirestore.getInstance()
    private val noticias = mutableListOf<Noticia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_home)

        recycler = findViewById(R.id.recyclerNoticias)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(noticias) { noticia ->
            val i = Intent(this, VerNoticiaActivity::class.java)
            i.putExtra("id", noticia.id)
            i.putExtra("titulo", noticia.titulo)
            i.putExtra("resumen", noticia.resumen)
            i.putExtra("contenido", noticia.contenido)
            i.putExtra("autor", noticia.autor)
            i.putExtra("fecha", noticia.fecha)
            startActivity(i)
        }
        recycler.adapter = adapter

        // 🚀 BOTÓN PARA AGREGAR NOTICIAS
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            startActivity(Intent(this, AgregarNoticiaActivity::class.java))
        }

        cargarNoticias()
    }

    private fun cargarNoticias() {
        db.collection("noticias").get()
            .addOnSuccessListener { result ->
                noticias.clear()
                for (doc in result) {
                    val n = doc.toObject(Noticia::class.java)
                    n.id = doc.id
                    noticias.add(n)
                }
                adapter.update(noticias)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar noticias: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
