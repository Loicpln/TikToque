package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.google.android.gms.common.api.Response
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivitySignUpBinding
import fr.isen.tiktoque.model.User
import java.util.*
import javax.security.auth.callback.Callback

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener{
            ajoutUser()
        }

    }
    private fun ajoutUser() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users")
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val user = User(username, email, password, Date().toString(), Date().toString())
            myRef.child(username).setValue(user)
    }
}