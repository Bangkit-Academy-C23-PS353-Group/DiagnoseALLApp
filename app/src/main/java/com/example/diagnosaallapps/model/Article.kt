package com.example.diagnosaallapps.model

import com.example.diagnosaallapps.R

data class Article(
    val imageArticle: Int,
    val textArticle: String,
)

val dummyArticles = listOf(
    Article(R.drawable.imagearticletwo, "Meminum air putih dapat..."),
    Article(R.drawable.imagearticletwo, "Berolahraga dapat membantu...")
)
