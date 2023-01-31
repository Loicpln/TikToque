package fr.isen.tiktoque

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.tiktoque.model.Post

class PostAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //private val image: ImageView? = view.findViewById(R.id.postImage)
        private val title: TextView? = view.findViewById(R.id.postTitle)
        private val adresse: TextView? = view.findViewById(R.id.postAdresse)
        private val phone: TextView? = view.findViewById(R.id.postTelephone)
        private val type: TextView? = view.findViewById(R.id.postType)
        private val description: TextView? = view.findViewById(R.id.postDescription)

        fun bind(elem: Post) {
            title?.text = elem.name
            adresse?.text = elem.adresse
            phone?.text = elem.phone
            type?.text = elem.type
            description?.text = elem.content
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