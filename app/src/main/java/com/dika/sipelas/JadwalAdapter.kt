package com.dika.sipelas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JadwalAdapter(private val listJadwal: ArrayList<Jadwal>) : RecyclerView.Adapter<JadwalAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvJam: TextView = itemView.findViewById(R.id.tv_jam)
        var tvMatkul: TextView = itemView.findViewById(R.id.tv_matkul)
        var tvKelas: TextView = itemView.findViewById(R.id.tv_kelas)
        var tvKet: TextView = itemView.findViewById(R.id.tv_keterangan)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_jadwal, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listJadwal.size
    }

    override fun onBindViewHolder(holder: JadwalAdapter.ListViewHolder, position: Int) {
        val jadwal = listJadwal[position]

        holder.tvJam.text = jadwal.jamAwal.toString()
        holder.tvMatkul.text = jadwal.matkul
        holder.tvKelas.text = jadwal.kelas
        holder.tvKet.text = jadwal.keterangan
    }
}