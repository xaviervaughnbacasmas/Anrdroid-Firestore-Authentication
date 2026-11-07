package com.example.home_page_layout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    // I-declare ang Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // I-initialize ang Firebase Auth
        auth = FirebaseAuth.getInstance()

        val tvUserEmail = findViewById<TextView>(R.id.tvUserEmail)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Kuhaon ang email sa kasamtangang user ug ipakita sa TextView
        val currentUser = auth.currentUser
        if (currentUser != null) {
            tvUserEmail.text = "Logged in as: ${currentUsgit add .er.email}"
        }

        // Logic kon pinduton ang Logout button
        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}