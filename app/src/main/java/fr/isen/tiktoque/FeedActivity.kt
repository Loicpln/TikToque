package fr.isen.tiktoque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivityFeedBinding
import fr.isen.tiktoque.model.Restaurants

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        auth.currentUser

        Firebase.database.reference.child("restaurants").get().addOnSuccessListener {
            val restaurants = it.children.map { e -> e.getValue(Restaurants::class.java) }
            binding.feed.adapter = PostAdapter(restaurants as ArrayList<Restaurants>)
        }


    }
}