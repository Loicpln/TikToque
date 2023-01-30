package fr.isen.tiktoque.model

import java.io.Serializable

data class Restaurants(
    var name: String,
    var address: String,
    var image: String,
    var phone: String,
    var type: String
) : Serializable
