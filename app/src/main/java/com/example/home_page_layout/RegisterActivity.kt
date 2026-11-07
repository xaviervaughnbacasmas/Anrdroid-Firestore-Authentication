package com.example.home_page_layout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    // I-declare ang Firebase Authentication variable
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // I-initialize ang Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Kuhaon ang mga views gikan sa activity_register.xml
        val etRegEmail = findViewById<EditText>(R.id.etRegEmail)
        val etRegPassword = findViewById<EditText>(R.id.etRegPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)

        // Logic kon i-click ang Register button
        btnRegister.setOnClickListener {
            val email = etRegEmail.text.toString().trim()
            val password = etRegPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Palihog sulati tanan fields", Toast.LENGTH_SHORT).show()
            } else {
                // Himoong bag-ong user sa Firebase
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Naka-register na!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Napakyas: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        // Pinduton para mobalik sa Login Screen
        tvGoToLogin.setOnClickListener {
            finish()
        }
    }
}