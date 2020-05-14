package com.dika.sipelas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Notifikasi : AppCompatActivity() {
    private lateinit var rvNotif: RecyclerView
    private lateinit var list: ArrayList<Notif>
    lateinit var ref : DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).title = "Notifikasi"

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
                val nama = user?.nama

                ref = FirebaseDatabase.getInstance().getReference("Pinjam")
                list = arrayListOf()
                rvNotif = findViewById(R.id.rv_notif)
                var rif = ref.orderByChild("nama").equalTo(nama.toString())

                rif.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0!!.exists()){

                            for (h in p0.children){
                                val notif = h.getValue(Notif::class.java)
                                list.add(notif!!)
                            }

                            showRecyclerList()
                        }
                    }
                })
            }
        }
        reff.addListenerForSingleValueEvent(menuListener)
    }

    private fun showRecyclerList(){
        rvNotif.layoutManager = LinearLayoutManager(this)
        val notifAdapter = NotifAdapter(list)
        rvNotif.adapter = notifAdapter
    }
}
