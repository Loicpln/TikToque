package fr.isen.tiktoque

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import fr.isen.tiktoque.model.Comment
import fr.isen.tiktoque.model.User
import java.io.File

class CommentAdapter(private val comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView? = view.findViewById(R.id.image)
        private val name: TextView? = view.findViewById(R.id.name)
        private val content: TextView? = view.findViewById(R.id.content)

        fun bind(comment: Comment) {
            val localFile = File.createTempFile("images", "jpg")
            Firebase.database.getReference("users/${comment.uid}").get().addOnSuccessListener {
                val user = it.getValue<User>()
                name?.text = user?.username
                FirebaseStorage.getInstance().reference.child("images/${user?.photo}").getFile(localFile).addOnSuccessListener {
                    Picasso.get().load(localFile).into(image)
                }
            }
            content?.text = comment.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_comment, parent, false))
    }

    override fun getItemCount() : Int = comments.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])

    }


}
