package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_peminjaman.*
import kotlinx.android.synthetic.main.activity_detail_peminjaman.et_alasan
import kotlinx.android.synthetic.main.activity_detail_peminjaman.et_id
import kotlinx.android.synthetic.main.activity_detail_peminjaman.et_kelas
import kotlinx.android.synthetic.main.activity_detail_peminjaman.et_nama
import kotlinx.android.synthetic.main.activity_form_peminjaman.*
import kotlinx.android.synthetic.main.activity_ruangan.*

class DetailPeminjaman : AppCompatActivity() {
    companion object{
        const val EXTRA_ID = "extra_id"
    }
    lateinit var reff : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_peminjaman)

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).title = "Detail Peminjaman"

        var id = intent.getStringExtra(EXTRA_ID)
        var pinjam: Pinjam? = null
        var ref = FirebaseDatabase.getInstance().getReference("Pinjam").child(id)
        // Read from the database
        val menuListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                pinjam = dataSnapshot.getValue(Pinjam::class.java)
                et_nama.setText(pinjam?.nama)
                et_id.setText(pinjam?.id)
                et_kelas.setText(pinjam?.kelas)
                et_matkul.setText(pinjam?.matkul)
                et_tanggal.setText(pinjam?.tanggal)
                et_jamAwal.setText(pinjam?.jAwal)
                et_jamAkhir.setText(pinjam?.jAkhir)
                et_ruang.setText(pinjam?.ruang)
                et_alasan.setText(pinjam?.alasan)
                et_keterangan.setText(pinjam?.keterangan)

                reff = FirebaseDatabase.getInstance().getReference("Ruang")
                bt_terima.setOnClickListener {
                    val id_pinjam = pinjam?.id.toString()
                    val hari = pinjam?.hari.toString()
                    val ruang = pinjam?.ruang.toString()
                    val jam = pinjam?.jAwal.toString()
                    val id_ruang = hari+"_"+ruang+"_"+jam
                    var hari_ruang = hari+"_"+ruang

                    val ruangan = Ruang(hari, ruang, jam, "terisi", hari_ruang)
                    reff.child(id_ruang).setValue(ruangan).addOnCompleteListener {
                        Toast.makeText(this@DetailPeminjaman, "Successs", Toast.LENGTH_SHORT).show()
                    }

                    ref.child("status").setValue("diterima").addOnCompleteListener {
                        Toast.makeText(this@DetailPeminjaman, "Success",Toast.LENGTH_SHORT).show()
                    }

                    val intent = Intent (this@DetailPeminjaman, dataPeminjaman::class.java)
                    startActivity(intent)
                }

                bt_tolak.setOnClickListener {
                    val id = pinjam?.id.toString()
                    ref.child(id).child("status").setValue("ditolak").addOnCompleteListener {
                        Toast.makeText(this@DetailPeminjaman, "Success",Toast.LENGTH_SHORT).show()
                    }

                    val intent = Intent (this@DetailPeminjaman, dataPeminjaman::class.java)
                    startActivity(intent)
                }
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}
