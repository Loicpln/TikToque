package fr.isen.tiktoque

import android.content.ClipData
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import fr.isen.tiktoque.databinding.ActivityLoginBinding
import fr.isen.tiktoque.model.User
import java.io.File

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.submitButton.setOnClickListener{
            signIn(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Snackbar.make(binding.root, "Authentication failed.", Snackbar.LENGTH_SHORT)
                            .show()
                        updateUI(null)
                    }
                }
        }else{
            Snackbar.make(binding.root, "Please fill all the fields", Snackbar.LENGTH_SHORT).show()
        }

    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Snackbar.make(binding.root, "User connected", Snackbar.LENGTH_SHORT).show()
            startActivity(Intent(this, FeedActivity::class.java))
        }
    }


}