package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.tiktoque.databinding.ActivityUserInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import fr.isen.tiktoque.model.User
import java.io.File
import java.util.*


class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var uuidPhoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveUserInfo.setOnClickListener { saveUserInfo() }
        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()

        binding.saveButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        Firebase.database.getReference("users/${auth.currentUser?.uid}").get().addOnSuccessListener {
            val user = it.getValue<User>()
            val localFile = File.createTempFile("images", "jpg")
            binding.userUsername.setText(user?.username)
            binding.userAge.setText(user?.age)
            binding.userPhone.setText(user?.phone)
            uuidPhoto = user?.photo.toString()
            FirebaseStorage.getInstance().reference.child("images/${user?.photo}").getFile(localFile).addOnSuccessListener {
                Picasso.get().load(localFile).into(binding.userProfileImage)
            }
        }

    }

   private fun saveUserInfo() {

       val username = binding.userUsername.text.toString()
       val age = binding.userAge.text.toString()
       val phone = binding.userPhone.text.toString()

       val currentUser = auth.currentUser
       val userId = currentUser?.uid ?: return
       val userReference = database.getReference("users").child(userId)
       val user = User(userId, username, age, phone, uuidPhoto)
       if(username.isEmpty() || age.isEmpty() || phone.isEmpty() || uuidPhoto.isEmpty()) {
           Snackbar.make(binding.root, "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG).show()
       }else{
           userReference.setValue(user)
           Snackbar.make(binding.root, "Profil actualisé", Snackbar.LENGTH_LONG).show()
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
                Snackbar.make(binding.root, "Image téléchargée", Snackbar.LENGTH_LONG).show()
            }.addOnFailureListener {
                Snackbar.make(binding.root, "Erreur lors du téléchargement", Snackbar.LENGTH_LONG).show()
            }
            binding.userProfileImage.setImageURI(selectedImage)
        }
    }
}