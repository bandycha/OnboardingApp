package com.example.onboardingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.onboardingapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.signUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

        private fun doLogin(){
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
            auth.signInWithEmailAndPassword(binding.edUsername.text.toString(), binding.edPassword.text.toString())
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

        /* val login = findViewById<Button>(R.id.btn_login)
        val username = findViewById<TextView>(R.id.edUsername)
        val password = findViewById<TextView>(R.id.edPassword)
        val signup = findViewById<TextView>(R.id.signUp)

        binding.btnLogin.setOnClickListener {

            if (binding.edUsername.text.trim().isNotEmpty() || binding.edPassword.text.trim()
                    .isNotEmpty()
            ) {

                signIn()

            } else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
            }
        }
        binding.signUp.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun signIn() {

        auth.signInWithEmailAndPassword(
            binding.edUsername.text.trim().toString(),
            binding.edPassword.text.trim().toString()
        )

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Authentication Error" + task.exception, Toast.LENGTH_LONG)
                        .show()
                }
            }
    }






       // if (user != null) {
       //     val intent = Intent(this, DashboardActivity::class.java)
        //    startActivity(intent)
     //   } else {
         //   Toast.makeText(this, "User first time login", Toast.LENGTH_LONG).show()
       // }*/

   public override fun onStart() {
      super.onStart()
      val currentUser = auth.currentUser
       updateUI(currentUser)
   }

   private fun updateUI (currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
            startActivity(Intent(this,DashboardActivity::class.java))
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


