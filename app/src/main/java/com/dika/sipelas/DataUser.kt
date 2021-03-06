package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_data_user.*

class DataUser : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    private lateinit var list: ArrayList<User>
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_user)

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).title = "Data User"

        ref = FirebaseDatabase.getInstance().getReference("users")
        list = arrayListOf()
        rvUser = findViewById(R.id.rv_user)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for (h in p0.children){
                        val user = h.getValue(User::class.java)
                        list.add(user!!)
                    }

                    showRecyclerList()
                }
            }
        })
    }

    private fun showRecyclerList(){
        rvUser.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(list)
        rvUser.adapter = userAdapter
    }
}
