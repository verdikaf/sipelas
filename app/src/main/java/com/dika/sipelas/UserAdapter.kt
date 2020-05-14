package com.dika.sipelas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val listMobil: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tv_nama)
        var tvId: TextView = itemView.findViewById(R.id.tv_id)
        var tvTelp: TextView = itemView.findViewById(R.id.tv_telp)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMobil.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val mobil = listMobil[position]

        holder.tvNama.text = mobil.nama
        holder.tvId.text = mobil.nim
        holder.tvTelp.text = mobil.telp
    }
}