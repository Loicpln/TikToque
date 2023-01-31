package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivityFeedBinding
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
                var posts = ArrayList<Post>()
                snapshot.children.forEach {
                    val post = Post(it.key!!,
                        it.child("name").getValue<String>()!!,
                        it.child("adresse").getValue<String>()!!,
                        it.child("phone").getValue<String>()!!,
                        it.child("content").getValue<String>()!!,
                        it.child("type").getValue<String>()!!,
                        it.child("date").getValue<Long>()!!)
                    posts.add(post)
                }
                binding.postList.adapter = PostAdapter(posts)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        binding.createPostButton.setOnClickListener {
            val intent = Intent(this, createPostActivity::class.java)
            startActivity(intent)
        }


    }
}