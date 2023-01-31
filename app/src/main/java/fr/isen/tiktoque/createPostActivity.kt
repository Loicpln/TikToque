package fr.isen.tiktoque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivityCreatePostBinding
import fr.isen.tiktoque.model.Post
import java.util.*

class createPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()



        auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val myRef = database.getReference("posts")




        binding.publish.setOnClickListener {
            //generer un post avec un id unique
            val postId = myRef.push().key
            //recuperer l'id de l'utilisateur
            val userId = auth.currentUser?.uid
            //recuperer le contenu du post
            val postContent = binding.post.text.toString()
            //recuperer le nom de l'utilisateur
            val nomRestau = binding.nomRestau.text.toString()
            //recuperer le numero de l'utilisateur
            val phone = binding.numeroRestau.text.toString()
            //recuperer l'adresse de l'utilisateur
            val adresse = binding.adresseRestau.text.toString()
            val type = binding.typeRestau.selectedItem.toString()
            //recuperer l'image de l'utilisateur
            val userImage = binding.choisirPhoto.text.toString()
            //creer un objet post
            val post = Post(userId, nomRestau, adresse, phone, postContent, type, userImage, Date())
            //ajouter le post a la base de donnees
            myRef.child(postId!!).setValue(post)
            //ajouter le post a la liste des posts de l'utilisateur
            val userRef = database.getReference("users").child(userId!!)
            userRef.child("posts").child(postId).setValue(post)
            //ajouter le post a la liste des posts de la timeline
            val timelineRef = database.getReference("timeline")
            timelineRef.child(postId).setValue(post)
        }

    }
}