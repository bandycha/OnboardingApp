package com.example.onboardingapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onboardingapp.databinding.ActivityFormalitiesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FormalitiesActivity : AppCompatActivity() {



    private lateinit var binding: ActivityFormalitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormalitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editorPref = sharedPref.edit()

        binding.savebtn.setOnClickListener{
            val medicalDocs = binding.medical.isChecked
            val form = binding.form.isChecked

                editorPref.apply {
                putBoolean("isChecked", medicalDocs)
                putBoolean("isChecked1", form)
                apply()

                    Toast.makeText(baseContext, "Zapisano", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loadbtn.setOnClickListener{
            val medicalDocs = sharedPref.getBoolean("isChecked", false)
            val form = sharedPref.getBoolean("isChecked1",false)

            binding.form.isChecked = form
            binding.medical.isChecked = medicalDocs
        }

    }

}