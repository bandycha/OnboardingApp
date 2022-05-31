package com.example.onboardingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingapp.databinding.ActivityDashboardBinding
import com.example.onboardingapp.databinding.ActivityMainBinding

class DashboardItemsAdapter(private val dashboardItems: List<DashboardItem>) :
RecyclerView.Adapter<DashboardItemsAdapter.DashboardItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
       return DashboardItemViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.dashboard_item_container,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: DashboardItemViewHolder, position: Int) {
       holder.bind(dashboardItems[position])
    }

    override fun getItemCount(): Int {
        return dashboardItems.size
    }

    inner class DashboardItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val imageDashboard = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind (dashboardItem: DashboardItem){
            imageDashboard.setImageResource(dashboardItem.dashboardImage)
            textTitle.text = dashboardItem.title
            textDescription.text = dashboardItem.description
        }
    }


}

