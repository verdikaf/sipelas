package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var tv_uid : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (supportActionBar as ActionBar).title = "Login"

        auth = FirebaseAuth.getInstance()

        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        tv_uid = findViewById(R.id.tv_uid)
    }

    fun siginexecution(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                if (!task.isSuccessful) {
                    tv_uid.text = "Authentication failed!"
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

        tv_uid.setText(user?.uid.toString())

        var user: User? = null  // declare user object outside onCreate Method

        var ref = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        // Read from the database
        val menuListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue(User::class.java)
                if (user?.status == "admin"){
                    val intent = Intent(this@MainActivity, DashboardAdmin::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@MainActivity, DashboardUser::class.java)
                    startActivity(intent)
                }
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    fun bt_login(view: View) {
        var user :String = et_username.text.toString()
        var password :String = et_password.text.toString()

        if (user.length > 7) {
            if (password.length > 7) {
                siginexecution(user,password)
            }
        }
    }

    fun bt_signup(view: View) {
        val intent = Intent(this@MainActivity, SignUp::class.java)
        startActivity(intent)
    }

}
