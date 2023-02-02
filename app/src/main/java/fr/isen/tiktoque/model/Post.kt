package fr.isen.tiktoque.model

import android.media.Image
import kotlin.collections.ArrayList

data class Post(
    var id: String? = "",
    var posterId: String? = "",
    var name: String = "",
    var adresse: String = "",
    var phone: String = "",
    var content: String = "",
    var type: String = "",
    var date: Long = 0,
    var image: String = "",
    var likes: Like = Like(),
    var comments: ArrayList<Comment> = ArrayList()
)