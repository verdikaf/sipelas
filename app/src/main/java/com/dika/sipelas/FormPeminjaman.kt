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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_form_peminjaman.*
import java.util.*

class FormPeminjaman : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var datePicker: DatePickerHelper
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_peminjaman)

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).title = "Form Peminjaman"

        auth = FirebaseAuth.getInstance()
        val users = auth.currentUser
        var user: User? = null  // declare user object outside onCreate Method
        var reff = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        // Read from the database
        val menuListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)
                et_nama.setText(user?.nama)
            }
        }
        reff.addListenerForSingleValueEvent(menuListener)

        datePicker = DatePickerHelper(this)
        bt_tanggal.setOnClickListener {
            showDatePickerDialog()
        }

        val arrayruang = resources.getStringArray(R.array.ruang)
        val spinnerruang = findViewById<Spinner>(R.id.sp_ruang)
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
                        this@FormPeminjaman,
                        getString(R.string.ruang) + " " +
                                "" + arrayruang[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arrayjamAwal = resources.getStringArray(R.array.jam)
        val spinnerjamAwal = findViewById<Spinner>(R.id.sp_jamAwal)
        if (spinnerjamAwal != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayjamAwal
            )
            spinnerjamAwal.adapter = adapter
            spinnerjamAwal.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@FormPeminjaman,
                        getString(R.string.jam_pinjam) + " " +
                                "" + arrayjamAwal[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arrayjamAkhir = resources.getStringArray(R.array.jam)
        val spinnerjamAkhir = findViewById<Spinner>(R.id.sp_jamAkhir)
        if (spinnerjamAkhir != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayjamAkhir
            )
            spinnerjamAkhir.adapter = adapter
            spinnerjamAkhir.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@FormPeminjaman,
                        getString(R.string.jam_selesai) + " " +
                                "" + arrayjamAkhir[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arraymatkul = resources.getStringArray(R.array.matkul)
        val spinnermatkul = findViewById<Spinner>(R.id.sp_matkul)
        if (spinnermatkul != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arraymatkul
            )
            spinnermatkul.adapter = adapter
            spinnermatkul.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@FormPeminjaman,
                        getString(R.string.mata_kuliah) + " " +
                                "" + arraymatkul[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val arrayketerangan = resources.getStringArray(R.array.status_ruang)
        val spinnerketerangan = findViewById<Spinner>(R.id.sp_status_ruang)
        if (spinnerketerangan != null) {
            val adapter = ArrayAdapter(                this,
                android.R.layout.simple_spinner_item, arrayketerangan
            )
            spinnerketerangan.adapter = adapter
            spinnerketerangan.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@FormPeminjaman,
                        getString(R.string.keterangan) + " " +
                                "" + arrayketerangan[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        ref = FirebaseDatabase.getInstance().getReference("Pinjam")
        bt_pinjam.setOnClickListener {
            savedata()
            val intent = Intent (this, DashboardUser::class.java)
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.HOUR_OF_DAY, 0)
        minDate.set(Calendar.MINUTE, 0)
        minDate.set(Calendar.SECOND, 0)
        datePicker.setMinDate(minDate.timeInMillis)
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 7)
        datePicker.setMaxDate(maxDate.timeInMillis)
        datePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                bt_tanggal.text = "${dayStr}-${monthStr}-${year}"
            }
        })
    }

    private fun savedata() {

        val id = et_id.text.toString()
        val nama = et_nama.text.toString()
        val kelas = et_kelas.text.toString()
        val tanggal = bt_tanggal.text.toString()
        val ruang = sp_ruang.getSelectedItem().toString()
        val jAwal = sp_jamAwal.getSelectedItem().toString()
        val jAkhir = sp_jamAkhir.getSelectedItem().toString()
        val matkul = sp_matkul.getSelectedItem().toString()
        val keterangan = sp_status_ruang.getSelectedItem().toString()
        val alasan = et_alasan.text.toString()

        val peminjaman = Peminjaman(id,nama,kelas,tanggal,ruang,jAwal,jAkhir,matkul,alasan, keterangan, "menunggu")

        ref.child(id).setValue(peminjaman).addOnCompleteListener {
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
            et_id.setText("")
            et_nama.setText("")
            et_kelas.setText("")
            bt_tanggal.setText("")
            et_alasan.setText("")
        }
    }
}
