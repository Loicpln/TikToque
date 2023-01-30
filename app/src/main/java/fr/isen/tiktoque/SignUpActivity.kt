package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.api.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivitySignUpBinding
import fr.isen.tiktoque.model.User
import java.util.*
import javax.security.auth.callback.Callback

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        auth.addAuthStateListener {
            updateUI(it.currentUser)
        }

        binding.submitButton.setOnClickListener{
            signUp(binding.email.text.toString(), binding.password.text.toString())
        }

    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // L'inscription a réussi, mettez à jour l'interface utilisateur
                    updateUI(auth.currentUser)
                } else {
                    // L'inscription a échoué, affichez un message d'erreur
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // L'utilisateur est connecté, affichez les informations de l'utilisateur
            //TODO
        } else {
            // L'utilisateur n'est pas connecté, affichez un message approprié
            //TODO
        }
    }

    /*private fun ajoutUser() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users")
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val user = User(username, email, password, Date().toString(), Date().toString())
            myRef.child(username).setValue(user)
    }*/
}