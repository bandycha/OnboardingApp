package com.example.onboardingapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.onboardingapp.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

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

        // TODO save username during registration
        // TODO use string resources instead of hardcoded strings
        val title = when (val name = FirebaseAuth.getInstance().currentUser?.displayName) {
            null -> "Hej!"
            else -> "Hej $name!"
        }
        binding.homeToolbar.title = title
        setSupportActionBar(binding.homeToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_item_logout -> {
            logout()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}