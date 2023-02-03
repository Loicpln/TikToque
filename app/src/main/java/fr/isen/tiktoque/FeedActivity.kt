package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivityFeedBinding
import fr.isen.tiktoque.model.Comment
import fr.isen.tiktoque.model.Like
import fr.isen.tiktoque.model.Post
import kotlin.collections.ArrayList

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        binding = ActivityFeedBinding.inflate(layoutInflater)
        binding.postList.layoutManager = LinearLayoutManager(null)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        Firebase.database.getReference("posts").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val posts = snapshot.children.map {
                    val post = it.getValue<Post>()
                    post?.id = it.key.toString()
                    if (post?.comments == null) {
                        post?.comments = ArrayList()
                    }
                    if(post?.likes?.users == null) {
                        post?.likes?.users = ArrayList()
                    }
                    return@map post ?: Post()
                }
                val reverse = ArrayList(posts.reversed())
                if (reverse.isNotEmpty()) {
                    binding.postList.adapter = PostAdapter(reverse, auth.currentUser?.uid!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        binding.createPostButton.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }


    }
}