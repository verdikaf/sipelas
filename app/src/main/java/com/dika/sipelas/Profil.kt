package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Profil : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    companion object{
        const val EXTRA_UID = "extra_uid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        auth = FirebaseAuth.getInstance()
        val users = auth.currentUser

        val et_id : EditText = findViewById(R.id.et_id)
        val et_nama : EditText = findViewById(R.id.et_nama)
        val et_email : EditText = findViewById(R.id.et_email)
        val et_telp : EditText = findViewById(R.id.et_telp)
        val et_status : EditText = findViewById(R.id.et_status)

//        et_id.setText(users?.uid.toString())

        var user: User? = null  // declare user object outside onCreate Method

        var ref = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        // Read from the database
        val menuListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)
                et_nama.setText(user?.nama)
                et_id.setText(user?.nim)
                et_status.setText(user?.status)
                et_telp.setText(user?.telp)
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    fun bt_logout(view: View) {
        FirebaseAuth.getInstance().signOut()

        val intentLogout = Intent(this@Profil, MainActivity::class.java)
        startActivity(intentLogout)
    }
}
