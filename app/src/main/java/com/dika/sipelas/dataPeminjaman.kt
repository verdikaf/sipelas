package com.dika.sipelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class dataPeminjaman : AppCompatActivity() {
    private lateinit var rvPinjam: RecyclerView
    private lateinit var list: ArrayList<Pinjam>
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_peminjaman)

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).title = "Data Peminjaman"

        ref = FirebaseDatabase.getInstance().getReference("Pinjam")
        list = arrayListOf()
        rvPinjam = findViewById(R.id.rv_pinjam)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for (h in p0.children){
                        val pinjam = h.getValue(Pinjam::class.java)
                        list.add(pinjam!!)
                    }

                    showRecyclerList()
                }
            }
        })
    }

    private fun showRecyclerList(){
        rvPinjam.layoutManager = LinearLayoutManager(this)
        val peminjamanAdapter = PeminjamanAdapter(list)
        rvPinjam.adapter = peminjamanAdapter
    }
}
