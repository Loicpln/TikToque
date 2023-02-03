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

        Firebase.database.getReference("posts").get().addOnSuccessListener {
            val posts = it.children.map {elem ->
                val post = elem.getValue<Post>()
                post?.id = elem.key.toString()
                return@map post ?: Post()
            }
            binding.postList.adapter = PostAdapter(ArrayList(posts.reversed()), auth.currentUser?.uid!!)
        }

        Firebase.database.getReference("posts").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        binding.createPostButton.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        binding.editProfileButton.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
        }
        binding.logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}