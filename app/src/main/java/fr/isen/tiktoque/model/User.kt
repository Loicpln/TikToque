package fr.isen.tiktoque.model

import java.io.Serializable

data class User(
    var id: Int,
    var username: String,
    var email: String,
    var password: String,
    var create_date: String,
    var update_date: String
) : Serializable
