package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivitySignUpBinding
import fr.isen.tiktoque.model.User

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.submitButton.setOnClickListener{
            signUp(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
            } else {
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val username = binding.username.text.toString()
            if (username.isNotEmpty() && binding.email.text.toString().isNotEmpty() && binding.password.text.toString().isNotEmpty()) {
                Firebase.database.getReference("users").child(user.uid).setValue(User(user.uid, username, "","",""))
                startActivity(Intent(this, FeedActivity::class.java))
                Snackbar.make(binding.root, "User created", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(binding.root, "Please fill all the fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}