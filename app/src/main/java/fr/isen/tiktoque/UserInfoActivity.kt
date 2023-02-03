package fr.isen.tiktoque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.tiktoque.databinding.ActivityUserInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import fr.isen.tiktoque.model.User


class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var uuidPhoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveUserInfo.setOnClickListener { saveUserInfo() }
    }


    private fun saveUserInfo() {
        val username = binding.userUsername.text.toString().trim()
        val age = binding.userAge.text.toString().trim()
        val phone = binding.userPhone.text.toString().trim()

        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("users")
        val userId = reference.push().key
        val user = User(userId, username, age, phone)
        if (userId != null) {
            reference.child(userId).setValue(user)
        }
    }
}