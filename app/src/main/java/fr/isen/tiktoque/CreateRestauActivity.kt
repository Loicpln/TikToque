package fr.isen.tiktoque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.tiktoque.databinding.ActivityCreateRestauBinding

class CreateRestauActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRestauBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_restau)

        binding = ActivityCreateRestauBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}