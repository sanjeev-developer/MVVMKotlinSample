package com.example.mvvmkotlinsample.models

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)