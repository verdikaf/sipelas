package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar

class DashboardAdmin : AppCompatActivity() {

    private lateinit var bt_profil: Button
    private lateinit var bt_pinjam: Button
    private lateinit var bt_jadwal: Button
    private lateinit var bt_user: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_admin)

        (supportActionBar as ActionBar).title = "Dashboard"

        bt_profil = findViewById(R.id.bt_profil)
        bt_profil.setOnClickListener{
            startActivity(Intent(this, Profil::class.java))
        }

        bt_pinjam = findViewById(R.id.bt_pinjam)
        bt_pinjam.setOnClickListener{
            startActivity(Intent(this, dataPeminjaman::class.java))
        }

        bt_user = findViewById(R.id.bt_user)
        bt_user.setOnClickListener{
            startActivity(Intent(this, DataUser::class.java))
        }

        bt_jadwal = findViewById(R.id.bt_jadwal)
        bt_jadwal.setOnClickListener{
            startActivity(Intent(this, DataJadwal::class.java))
        }
    }
}
