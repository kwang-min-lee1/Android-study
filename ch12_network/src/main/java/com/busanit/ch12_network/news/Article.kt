package com.busanit.ch12_network.news

data class Article (
    val title: String,
    val description: String,
    val urlToImage: String?
)

data class NewsResponse(
    val articles: List<Article>

)
