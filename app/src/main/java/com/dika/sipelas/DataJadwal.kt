package com.dika.sipelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_data_jadwal.*

class DataJadwal : AppCompatActivity() {
    private lateinit var rvJadwal: RecyclerView
    private lateinit var list: ArrayList<Jadwal>
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_jadwal)

        (supportActionBar as ActionBar).title = "Data Jadwal"

        ref = FirebaseDatabase.getInstance().getReference("pinjam")
        list = arrayListOf()
        rvJadwal = findViewById(R.id.rv_jadwal)
        var rif = ref.orderByChild("tgl_ruang").equalTo("10-05-2020_2")

        rif.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for (h in p0.children){
                        val jadwal = h.getValue(Jadwal::class.java)
                        list.add(jadwal!!)
                    }

                    showRecyclerList()
                }
            }
        })
    }

    private fun showRecyclerList(){
        rvJadwal.layoutManager = LinearLayoutManager(this)
        val jadwalAdapter = JadwalAdapter(list)
        rvJadwal.adapter = jadwalAdapter
    }
}
