package com.example.onboardingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingapp.databinding.ActivityFormalitiesBinding
import com.example.onboardingapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.formalities.setOnClickListener {
            val intent = Intent(this, FormalitiesActivity::class.java)
            startActivity(intent)

        }

        binding.benefits.setOnClickListener {
            val intent = Intent(this, BenefitsActivity::class.java)
            startActivity(intent)
        }

        binding.contactUs.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        binding.contactUs.setOnClickListener {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
    }
}