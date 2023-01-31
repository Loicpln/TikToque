package fr.isen.tiktoque.model

import java.util.*

data class Post(
    var id : String? = "",
    var name: String = "",
    var adresse: String = "",
    var phone: String = "",
    var content: String = "",
    var type: String = "",
    var image: String = "",
    var date: Date = Date(),
)