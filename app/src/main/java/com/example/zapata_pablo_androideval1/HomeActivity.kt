package com.example.zapata_pablo_androideval1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: NewsAdapter
    private val noticiasList = mutableListOf<Noticia>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recycler = findViewById(R.id.recyclerNoticias)
        recycler.layoutManager = LinearLayoutManager(this)

        adapter = NewsAdapter(noticiasList) { noticia ->
            val intent = Intent(this, VerNoticiaActivity::class.java)
            intent.putExtra("titulo", noticia.titulo)
            intent.putExtra("contenido", noticia.contenido)
            intent.putExtra("autor", noticia.autor)
            intent.putExtra("fecha", noticia.fecha)
            startActivity(intent)
        }

        recycler.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AgregarNoticiaActivity::class.java))
        }

        findViewById<ImageButton>(R.id.btnLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        cargarNoticias()
    }

    override fun onResume() {
        super.onResume()
        cargarNoticias()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun cargarNoticias() {
        db.collection("noticias")
            .get()
            .addOnSuccessListener { result ->
                noticiasList.clear()
                for (doc in result) {
                    noticiasList.add(
                        Noticia(
                            id = doc.id,
                            titulo = doc.getString("titulo") ?: "",
                            resumen = doc.getString("resumen") ?: "",
                            contenido = doc.getString("contenido") ?: "",
                            autor = doc.getString("autor") ?: "",
                            fecha = doc.getString("fecha") ?: ""
                        )
                    )
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al cargar noticias", Toast.LENGTH_SHORT).show()
            }
    }
}
