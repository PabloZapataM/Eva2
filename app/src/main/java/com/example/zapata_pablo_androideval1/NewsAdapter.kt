package com.example.zapata_pablo_androideval1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(
    private val noticias: List<Noticia>,
    private val onClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgNoticia: ImageView = itemView.findViewById(R.id.imgNoticia)
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvResumen: TextView = itemView.findViewById(R.id.tvResumen)
        val tvAutor: TextView = itemView.findViewById(R.id.tvAutor)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = noticias.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val noticia = noticias[position]

        // ⭐ Cargar imagen desde URL con Glide
        Glide.with(holder.itemView.context)
            .load(noticia.imagenUrl)
            .placeholder(R.drawable.noticia) // mientras carga
            .error(R.drawable.noticia)       // si falla la URL
            .into(holder.imgNoticia)

        holder.tvTitulo.text = noticia.titulo
        holder.tvResumen.text = noticia.resumen
        holder.tvAutor.text = "Autor: ${noticia.autor}"
        holder.tvFecha.text = "Fecha: ${noticia.fecha}"

        holder.itemView.setOnClickListener { onClick(noticia) }
    }
}
