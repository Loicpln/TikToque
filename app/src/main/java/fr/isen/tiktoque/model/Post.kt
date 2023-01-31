package fr.isen.tiktoque.model

import java.util.*

data class Post(
    val id : String?,
    val name: String,
    val adresse: String,
    val phone: String,
    val content: String,
    val image: String,
    val date: Date,
)
