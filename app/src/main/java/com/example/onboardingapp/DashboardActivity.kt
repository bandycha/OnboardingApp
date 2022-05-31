package com.example.onboardingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onboardingapp.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardItemsAdapter: DashboardItemsAdapter


    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setDashboardItems()
    }

    private fun setDashboardItems() {
        dashboardItemsAdapter = DashboardItemsAdapter(
            listOf(
                DashboardItem(
                    dashboardImage = R.drawable.page1, "Witaj w naszej firmie!",
                    description = "Cieszymy się, że pozytywnie przeszedłeś proces rekrutacyjny. Ta aplikacja pomoże Ci w zorganizowaniu swoich pierwszych dni w pracy."
                ),
                DashboardItem(
                    dashboardImage = R.drawable.page2, "Benefity",
                    description = "Znajdziesz tu informację o benefitach z których możesz korzystać już od 1 dnia pracy!"
                ),
                DashboardItem(
                    dashboardImage = R.drawable.page3, "Dokumenty",
                    description = "Możesz też sprawdzić, jakie formalności zostały Ci jeszcze do zrobienia, aby zakońćzyć proces zatrudnienia. W razie pytań możesz skontaktować się ze swoim rekruterem"
                )
            )
        )

        binding.onboardingViewPager.adapter = dashboardItemsAdapter

        binding.imageNext.setOnClickListener {
            if (binding.onboardingViewPager.currentItem +1 < dashboardItemsAdapter.itemCount) {
                binding.onboardingViewPager.currentItem += 1
            }else {
                navigateToHomeActivity()
            }
        }

        binding.textSkip.setOnClickListener{
            navigateToHomeActivity()

        }

        binding.buttonGetStarted.setOnClickListener{
            navigateToHomeActivity()

        }

    }




    private fun navigateToHomeActivity() {
        startActivity(Intent(applicationContext,HomeActivity::class.java))
        finish()
    }


    }
