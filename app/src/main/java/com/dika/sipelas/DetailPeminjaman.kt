package com.dika.sipelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_detail_peminjaman.*

class DetailPeminjaman : AppCompatActivity() {
    companion object{
        const val EXTRA_ID = "extra_id"
    }

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
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}
