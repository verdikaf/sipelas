package com.dika.sipelas

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeminjamanAdapter(private val listPinjam: ArrayList<Pinjam>) : RecyclerView.Adapter<PeminjamanAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tv_nama)
        var tvRuang: TextView = itemView.findViewById(R.id.tv_ruang)
        var tvTgl: TextView = itemView.findViewById(R.id.tv_tanggal)
        var tvStatus: TextView = itemView.findViewById(R.id.tv_status)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_pinjam, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPinjam.size
    }

    override fun onBindViewHolder(holder: PeminjamanAdapter.ListViewHolder, position: Int) {
        val pinjam = listPinjam[position]

        holder.tvNama.text = pinjam.nama
        holder.tvRuang.text = pinjam.ruang
        holder.tvTgl.text = pinjam.tanggal
        holder.tvStatus.text = pinjam.status
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailPeminjaman::class.java)
            intent.putExtra(DetailPeminjaman.EXTRA_ID, pinjam.id)
            holder.itemView.context.startActivity(intent)
        }
    }
}
