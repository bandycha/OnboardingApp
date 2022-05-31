package com.example.onboardingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingapp.databinding.ActivityFormalitiesBinding
import com.example.onboardingapp.databinding.ActivityRegisterBinding

class FormalitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormalitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormalitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}