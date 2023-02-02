package fr.isen.tiktoque

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import fr.isen.tiktoque.model.Comment
import fr.isen.tiktoque.model.Post
import java.io.File

class PostAdapter(private val posts: ArrayList<Post>, private val uid: String) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView? = view.findViewById(R.id.postImage)
        private val title: TextView? = view.findViewById(R.id.postTitle)
        private val adresse: TextView? = view.findViewById(R.id.postAdresse)
        private val phone: TextView? = view.findViewById(R.id.postTelephone)
        private val type: TextView? = view.findViewById(R.id.postType)
        private val description: TextView? = view.findViewById(R.id.postDescription)

        private val recyclerView: RecyclerView? = view.findViewById(R.id.recyclerView)
        private val like: Button? = view.findViewById(R.id.like)
        private val compteurLike: TextView? = view.findViewById(R.id.compteurLike)
        private val postComment: TextInputEditText? = view.findViewById(R.id.postComment)
        private val createCommentButton: Button? = view.findViewById(R.id.createCommentButton)

        fun bind(elem: Post, uid: String) {
            val storage = FirebaseStorage.getInstance().reference.child("images/${elem.image}")
            val localFile = File.createTempFile("images", "jpg")
            storage.getFile(localFile).addOnSuccessListener {
                Picasso.get().load(localFile).into(image)
            }
            title?.text = elem.name
            adresse?.text = elem.adresse
            phone?.text = elem.phone
            type?.text = elem.type
            description?.text = elem.content

            recyclerView?.layoutManager = LinearLayoutManager(null)
            recyclerView?.adapter = CommentAdapter(elem.comments)
            recyclerView?.scrollToPosition(elem.comments.size - 1)

            if(elem.likes.users.contains(uid)) {
                like?.background = like?.context?.getDrawable(R.drawable.favorite)
            }else {
                like?.background = like?.context?.getDrawable(R.drawable.favorite_border)
            }

            like?.setOnClickListener {
                val likeDb = Firebase.database.getReference("posts/${elem.id}/likes")
                if(elem.likes.users.contains(uid)) {
                    likeDb.child("count").setValue(elem.likes.count - 1)
                    likeDb.child("users").child(uid).removeValue()
                }else {
                    likeDb.child("count").setValue(elem.likes.count + 1)
                    likeDb.child("users").child(uid).setValue(uid)
                }

            }

            compteurLike?.text = "${elem.likes.count} likes"


            createCommentButton?.setOnClickListener {
                if (postComment?.text.toString() != "") {
                    val comment = Comment(uid, postComment?.text.toString())
                    Firebase.database.getReference("posts/${elem.id}/comments").push().setValue(comment)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_feed, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position], uid)
    }


}