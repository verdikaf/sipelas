package com.dika.sipelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var et_username: EditText
    lateinit var et_password: EditText
    lateinit var tv_uid : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

        tv_uid.setText(user?.uid.toString())

        val intentProfil = Intent(this@MainActivity, DashboardUser::class.java)
        startActivity(intentProfil)
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

}
