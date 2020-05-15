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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_peminjaman.*
import kotlinx.android.synthetic.main.activity_ruangan.*

class Ruangan : AppCompatActivity() {
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruangan)

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).title = "Form Ruangan"

        val arrayhari = resources.getStringArray(R.array.hari)
        val spinnerhari = findViewById<Spinner>(R.id.sp_hari)
        if (spinnerhari != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayhari
            )
            spinnerhari.adapter = adapter
            spinnerhari.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@Ruangan,
                        getString(R.string.ruang) + " " +
                                "" + arrayhari[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arrayruang = resources.getStringArray(R.array.ruang)
        val spinnerruang = findViewById<Spinner>(R.id.sp_ruangan)
        if (spinnerruang != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayruang
            )
            spinnerruang.adapter = adapter
            spinnerruang.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@Ruangan,
                        getString(R.string.ruang) + " " +
                                "" + arrayruang[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arrayjam = resources.getStringArray(R.array.jam)
        val spinnerjam = findViewById<Spinner>(R.id.sp_jam)
        if (spinnerjam != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayjam
            )
            spinnerjam.adapter = adapter
            spinnerjam.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@Ruangan,
                        getString(R.string.jam) + " " +
                                "" + arrayjam[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        ref = FirebaseDatabase.getInstance().getReference("Ruang")
        bt_ruang_input.setOnClickListener {
            savedata()
            val intent = Intent (this, DashboardAdmin::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {

        val hari = sp_hari.getSelectedItem().toString()
        val ruang = sp_ruangan.getSelectedItem().toString()
        val jam = sp_jam.getSelectedItem().toString()
        val id = hari+"_"+ruang+"_"+jam
        var hari_ruang = hari+"_"+ruang

        val ruangan = Ruang(hari, ruang, jam, "kosong", hari_ruang)

        ref.child(id).setValue(ruangan).addOnCompleteListener {
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
        }
    }
}
