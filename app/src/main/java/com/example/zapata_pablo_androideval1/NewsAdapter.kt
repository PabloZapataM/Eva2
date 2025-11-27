package com.example.zapata_pablo_androideval1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    private val noticias: List<Noticia>,
    private val onClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvResumen: TextView = itemView.findViewById(R.id.tvResumen)
        val tvAutor: TextView = itemView.findViewById(R.id.tvAutor)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount() = noticias.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.tvTitulo.text = noticia.titulo
        holder.tvResumen.text = noticia.resumen
        holder.tvAutor.text = "Autor: ${noticia.autor}"
        holder.tvFecha.text = "Fecha: ${noticia.fecha}"
        holder.itemView.setOnClickListener { onClick(noticia) }
    }
}
