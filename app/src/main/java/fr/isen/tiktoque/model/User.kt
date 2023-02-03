package fr.isen.tiktoque.model

data class User(
    val userId: String? = "" ,
    var username: String = "",
    val age: String = "",
    val phone: String = "",
    var photo: String = ""
)
