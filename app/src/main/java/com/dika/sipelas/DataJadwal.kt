package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_data_jadwal.*
import kotlinx.android.synthetic.main.activity_ruangan.*

class DataJadwal : AppCompatActivity() {
    private lateinit var rvJadwal: RecyclerView
    private lateinit var list: ArrayList<Jadwal>
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_jadwal)

        (supportActionBar as ActionBar).title = "Data Jadwal"

//        val arrayhari = resources.getStringArray(R.array.hari)
//        val spinnerhari = findViewById<Spinner>(R.id.sp_harii)
//        if (spinnerhari != null) {
//            val adapter = ArrayAdapter(                this,
//                android.R.layout.simple_spinner_item, arrayhari
//            )
//            spinnerhari.adapter = adapter
//            spinnerhari.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>,
//                    view: View, position: Int, id: Long
//                ) {
//                    Toast.makeText(
//                        this@DataJadwal,
//                        getString(R.string.ruang) + " " +
//                                "" + arrayhari[position], Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }
//
//        val arrayruang = resources.getStringArray(R.array.ruang)
//        val spinnerruang = findViewById<Spinner>(R.id.sp_ruangg)
//        if (spinnerruang != null) {
//            val adapter = ArrayAdapter(                this,
//                android.R.layout.simple_spinner_item, arrayruang
//            )
//            spinnerruang.adapter = adapter
//            spinnerruang.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>,
//                    view: View, position: Int, id: Long
//                ) {
//                    Toast.makeText(
//                        this@DataJadwal,
//                        getString(R.string.ruang) + " " +
//                                "" + arrayruang[position], Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }

        ref = FirebaseDatabase.getInstance().getReference("Ruang")

        list = arrayListOf()
        rvJadwal = findViewById(R.id.rv_jadwal)

//        bt_ruang_input.setOnClickListener {
//            val hari = sp_harii.getSelectedItem().toString()
//            val ruang = sp_ruangg.getSelectedItem().toString()
//            val hari_ruang = hari+"_"+ruang
//
//            var rif = ref.orderByChild("hari_ruang").equalTo(hari_ruang)
//
//            rif.addValueEventListener(object : ValueEventListener {
//                override fun onCancelled(p0: DatabaseError) {
//                }
//
//                override fun onDataChange(p0: DataSnapshot) {
//                    if (p0!!.exists()){
//
//                        for (h in p0.children){
//                            val jadwal = h.getValue(Jadwal::class.java)
//                            list.add(jadwal!!)
//                        }
//
//                        showRecyclerList()
//                    }
//                }
//            })
//        }

        ref.addValueEventListener(object : ValueEventListener {
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
