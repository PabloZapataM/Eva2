package com.example.zapata_pablo_androideval1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private var items: List<Noticia>, private val onClick: (Noticia) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsVH>() {
    inner class NewsVH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.item_title)
        val summary: TextView = view.findViewById(R.id.item_summary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NewsVH(v)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        val n = items[position]
        holder.title.text = n.titulo
        holder.summary.text = n.resumen
        holder.itemView.setOnClickListener { onClick(n) }
    }

    override fun getItemCount(): Int = items.size

    fun update(list: List<Noticia>) {
        this.items = list
        notifyDataSetChanged()
    }
}
