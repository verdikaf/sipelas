package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar

class DashboardUser : AppCompatActivity() {

    private lateinit var bt_profil: Button
    private lateinit var bt_pinjam: Button
    private lateinit var bt_jadwal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_user)

        (supportActionBar as ActionBar).title = "Dashboard"

        bt_profil = findViewById(R.id.bt_profil)
        bt_profil.setOnClickListener{
            startActivity(Intent(this, Profil::class.java))
        }

        bt_pinjam = findViewById(R.id.bt_pinjam)
        bt_pinjam.setOnClickListener{
            startActivity(Intent(this, FormPeminjaman::class.java))
        }

        bt_jadwal = findViewById(R.id.bt_jadwal)
        bt_jadwal.setOnClickListener{
            startActivity(Intent(this, DataJadwal::class.java))
        }
    }
}
