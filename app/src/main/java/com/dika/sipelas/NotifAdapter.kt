package com.dika.sipelas

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotifAdapter(private val listNotif: ArrayList<Notif>) : RecyclerView.Adapter<NotifAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMatkul: TextView = itemView.findViewById(R.id.tv_matkul)
        var tvRuang: TextView = itemView.findViewById(R.id.tv_ruang)
        var tvTgl: TextView = itemView.findViewById(R.id.tv_tanggal)
        var tvStatus: TextView = itemView.findViewById(R.id.tv_status)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_notif, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNotif.size
    }

    override fun onBindViewHolder(holder: NotifAdapter.ListViewHolder, position: Int) {
        val notif = listNotif[position]

        holder.tvMatkul.text = notif.matkul
        holder.tvRuang.text = notif.ruang
        holder.tvTgl.text = notif.tanggal
        holder.tvStatus.text = notif.status
    }
}