package com.geekbrains.md2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.md2.data.MainListItem

class MainListAdapter(var items: List<MainListItem>, val callback: Callback) : RecyclerView.Adapter<MainListAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))
    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }
    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewName = itemView.findViewById<TextView>(R.id.item_name)
        private val viewDisc = itemView.findViewById<TextView>(R.id.item_disc)
        private var viewImage = itemView.findViewById<ImageView>(R.id.item_image)
        fun bind(item: MainListItem) {
            viewName.text = item.name
            viewDisc.text = item.disc
            viewImage.setImageDrawable(item.img.drawable)
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }
    interface Callback {
        fun onItemClicked(item: MainListItem)
    }
}