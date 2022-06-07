package com.example.onboardingapp


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onboardingapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


@Suppress("NAME_SHADOWING")
class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        /*val backlogin = findViewById<TextView>(R.id.backLogin)
        val register = findViewById<Button>(R.id.register)
        val email = findViewById<TextView>(R.id.email)
       val editpassword = findViewById<TextView>(R.id.edcPassword)
        val confirmpassword = findViewById<TextView>(R.id.confirmPassword)*/


        binding.register.setOnClickListener {
            registerUser()
        }

        binding.backLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }

    private fun registerUser() {
        if (binding.email.text.toString().isEmpty()) {
            binding.email.error = "Please enter email"
            binding.email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()) {

            binding.email.error = "Please enter valid email"
            binding.email.requestFocus()
            return
        }

        if (binding.edcPassword.text.toString().isEmpty()) {

            binding.email.error = "Please enter password"
            binding.email.requestFocus()
            return
        }
        if (binding.confirmPassword.text.toString().isEmpty()) {

            binding.email.error = "Please confirm password"
            binding.email.requestFocus()
            return

        }

        auth.createUserWithEmailAndPassword(
            binding.email.text.toString(),
            binding.edcPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }

                } else {

                    Toast.makeText(
                        baseContext, "Registration failed. Try again after some time",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }


    }


}


/*   binding.register.setOnClickListener {

       if (binding.email.text.trim()
               .isNotEmpty() || binding.edcPassword.text.trim()
               .isNotEmpty() || binding.confirmPassword.text.trim().isNotEmpty()
       ) {


            registerUser()

       } else {

           Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()
       }

   }


   binding.backLogin.setOnClickListener {
       val intent = Intent(this, MainActivity::class.java)
       startActivity(intent)

   }
}
private fun registerUser() {
    val user = auth.currentUser

    auth.createUserWithEmailAndPassword(
       binding.email.text.trim().toString(),
       binding.edcPassword.text.trim().toString()
   )


       binding.register.setOnClickListener {
               user!!.sendEmailVerification()
                   .addOnCompleteListener{ task ->
if(task.isSuccessful){
Log.d(TAG, "Email sent.")}
                       else{
Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_LONG).show()
}

                   }

       }


}


private fun registerUser() {

 auth.createUserWithEmailAndPassword(
     binding.email.text.trim().toString(),
     binding.edcPassword.text.trim().toString()
 )


     .addOnCompleteListener(this) { task ->
         if (task.isSuccessful) {

             Toast.makeText(this, "Register successful", Toast.LENGTH_LONG).show()

             val user = auth.currentUser

             user!!.sendEmailVerification().addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         Log.d(TAG, "Email sent.")
                     }
                 }

         } else {

             Toast.makeText(
                 this,
                 "Register failed" + task.exception,
                 Toast.LENGTH_LONG
             )
                 .show()


         }
     }


}



//override fun onStart() {
//super.onStart()
//val user = auth.currentUser

//if (user != null) {

//val intent = Intent(this, DashboardActivity::class.java)
//startActivity(intent)
//} else {
//Log.e("user status", "User null")

//} */




