package com.example.onboardingapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onboardingapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun doLogin() {
        if (binding.edUsername.text.toString().isEmpty()) {
            binding.edUsername.error = "Please enter email"
            binding.edUsername.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edUsername.text.toString()).matches()) {

            binding.edUsername.error = "Please enter valid email"
            binding.edUsername.requestFocus()
            return
        }

        if (binding.edPassword.text.toString().isEmpty()) {

            binding.edUsername.error = "Please enter password"
            binding.edUsername.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(
            binding.edUsername.text.toString(),
            binding.edPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    baseContext, "Please verify your email address",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}


