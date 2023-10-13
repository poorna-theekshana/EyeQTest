package com.example.eyeqtest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeqtest.Modals.AstigmatismTestModal
import com.example.eyeqtest.R

class AstigmatismTestAdapter(private val testResults: List<AstigmatismTestModal>) :
    RecyclerView.Adapter<AstigmatismTestAdapter.TestResultViewHolder>() {

    // Create a ViewHolder class to hold the views for a single row
    class TestResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
        // Add more views as needed
    }

    // Create the ViewHolder and inflate the layout for each row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestResultViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_test_result, parent, false) // Create an XML layout for a single row
        return TestResultViewHolder(itemView)
    }

    // Bind data to the views in each row
    override fun onBindViewHolder(holder: TestResultViewHolder, position: Int) {
        val testResult = testResults[position]

        // Bind the data to the views
        holder.dateTextView.text = testResult.testDate
        holder.timeTextView.text = testResult.testTime
        holder.scoreTextView.text = testResult.Result
        // Add more binding as needed
    }

    // Return the number of items in the data set
    override fun getItemCount(): Int {
        return testResults.size
    }
}
