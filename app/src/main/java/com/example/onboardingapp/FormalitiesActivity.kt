package com.example.onboardingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onboardingapp.databinding.ActivityFormalitiesBinding

class FormalitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormalitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormalitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}