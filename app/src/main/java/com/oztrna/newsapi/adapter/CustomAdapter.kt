package com.oztrna.newsapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oztrna.newsapi.R
import com.oztrna.newsapi.model.Article
import com.oztrna.newsapi.model.Source
import kotlinx.android.synthetic.main.custom_row.view.*

class CustomAdapter(private val context: Context, private val data: List<Article>, private val clickListener: OnRowClickListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface OnRowClickListener {
        fun onRowClicked(article: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(data[position].urlToImage).into(holder.itemView.row_news_image)

        holder.itemView.row_news_title?.text = data[position].title
        holder.itemView.row_news_description?.text = data[position].description

        holder.itemView.setOnClickListener {
            clickListener.onRowClicked(data[position])
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}