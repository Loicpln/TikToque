package fr.isen.tiktoque

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth

class UserInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var uuidPhoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
    }
}