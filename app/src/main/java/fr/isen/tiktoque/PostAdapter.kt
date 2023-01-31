package fr.isen.tiktoque

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.LayoutFeedBinding
import fr.isen.tiktoque.model.Post

class PostAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //private val image: ImageView? = view.findViewById(R.id.postImage)
        private val title: TextView? = view.findViewById(R.id.postTitle)
        private val adresse: TextView? = view.findViewById(R.id.postAdresse)
        private val phone: TextView? = view.findViewById(R.id.postTelephone)
        private val type: TextView? = view.findViewById(R.id.postType)
        private val description: TextView? = view.findViewById(R.id.postDescription)

        private val postComment: TextInputEditText? = view.findViewById(R.id.postComment)
        private val createCommentButton: Button? = view.findViewById(R.id.createCommentButton)

        fun bind(elem: Post) {

            title?.text = elem.name
            adresse?.text = elem.adresse
            phone?.text = elem.phone
            type?.text = elem.type
            description?.text = elem.content
            createCommentButton?.setOnClickListener {
                Firebase.database.getReference("posts").child(elem.id!!).child("comments").push().setValue(postComment?.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_feed, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }


}