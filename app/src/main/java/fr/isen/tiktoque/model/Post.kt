package fr.isen.tiktoque.model

import android.widget.ImageView
import java.util.*
import kotlin.collections.ArrayList

data class Post(
    var id : String? = "",
    var name: String? = "",
    var adresse: String? = "",
    var phone: String? = "",
    var content: String? = "",
    var type: String? = "",
    var date: Long? = 0,
    var comments: ArrayList<String>? = ArrayList()
)