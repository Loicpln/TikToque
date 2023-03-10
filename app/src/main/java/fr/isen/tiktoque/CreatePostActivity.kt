package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import fr.isen.tiktoque.databinding.ActivityCreatePostBinding
import fr.isen.tiktoque.model.Like
import fr.isen.tiktoque.model.Post
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var postId: String
    private lateinit var uuidPhoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        //Instanciation du storage firebase
        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.reference


        auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val myRef = database.getReference("posts")
        //generer un post avec un id unique
        postId = myRef.push().key.toString()


        binding.choisirPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }


        binding.publish.setOnClickListener {

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
            //recuperer le type de restaurant
            val type = binding.typeRestau.selectedItem.toString()

            if (userId != null) {
                if (postContent.isNotEmpty() && nomRestau.isNotEmpty() && phone.isNotEmpty() && adresse.isNotEmpty() && type.isNotEmpty() && ::uuidPhoto.isInitialized) {
                    val post = Post(
                        postId,
                        userId,
                        nomRestau,
                        adresse,
                        phone,
                        postContent,
                        type,
                        Date().time,
                        uuidPhoto,
                        Like(0),
                        ArrayList()
                    )
                    myRef.child(postId).setValue(post)
                    Snackbar.make(binding.root, "Post publi??", Snackbar.LENGTH_LONG).show()
                    finish()
                } else {
                    Snackbar.make(binding.root, "Veuillez remplir tous les champs", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedImage = data?.data
        if (requestCode == 1 && resultCode == RESULT_OK && selectedImage != null) {
            uuidPhoto = UUID.randomUUID().toString()
            val imageRef = FirebaseStorage.getInstance().reference.child("images/${uuidPhoto}")
            imageRef.putFile(selectedImage).addOnSuccessListener {
                it.uploadSessionUri.let { imageRef.downloadUrl.addOnSuccessListener { it2 -> it2 } }
                Snackbar.make(binding.root, "Image t??l??charg??e", Snackbar.LENGTH_LONG).show()
            }.addOnFailureListener {
                Snackbar.make(binding.root, "Erreur lors du t??l??chargement", Snackbar.LENGTH_LONG).show()
            }
            binding.imageFromGallery.setImageURI(selectedImage)
        }
    }
}