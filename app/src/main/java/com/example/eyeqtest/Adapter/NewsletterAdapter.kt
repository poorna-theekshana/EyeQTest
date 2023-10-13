package com.example.eyeqtest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeqtest.Modals.NewsletterModal
import com.example.eyeqtest.R

class NewsletterAdapter(private val newsletterList: List<NewsletterModal>) :
    RecyclerView.Adapter<NewsletterAdapter.NewsletterViewHolder>() {

    class NewsletterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.newsletterTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.newsletterContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsletterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_newsletter, parent, false)
        return NewsletterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsletterViewHolder, position: Int) {
        val currentItem = newsletterList[position]
        holder.titleTextView.text = currentItem.title
        holder.contentTextView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return newsletterList.size
    }
}
