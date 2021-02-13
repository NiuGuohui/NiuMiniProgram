package com.aolig.popup.widget

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContentViewAdapter(private val contentView: View) :
    RecyclerView.Adapter<ContentViewAdapter.ContentHolder>() {
    inner class ContentHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun getItemCount() = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        return ContentHolder(contentView)
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
    }
}