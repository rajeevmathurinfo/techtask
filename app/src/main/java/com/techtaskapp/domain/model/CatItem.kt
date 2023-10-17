package com.techtaskapp.domain.model

data class CatItem(
    val breeds: List<Breed>?,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)