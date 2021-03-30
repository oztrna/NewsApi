package com.oztrna.newsapi.network

import com.oztrna.newsapi.model.Source

data class NewsResponse (
    val status: String,
    val totalResult: Int,
    val articles: List<Source>
)