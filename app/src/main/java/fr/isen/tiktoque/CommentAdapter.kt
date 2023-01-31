package fr.isen.tiktoque

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.tiktoque.model.Comment

class CommentAdapter(private val comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView? = view.findViewById(R.id.name)
        private val content: TextView? = view.findViewById(R.id.content)

        fun bind(elem: Comment) {
            name?.text = elem.name
            content?.text = elem.content
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
