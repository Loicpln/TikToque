package fr.isen.tiktoque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.tiktoque.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        binding.likeButton.setOnClickListener {
            binding.likeCount.text = (binding.likeCount.text.toString().toInt() + 1).toString()
        }

        binding.commentButton.setOnClickListener {
            val comment = binding.commentEditText.text.toString()
            binding.commentsContainer.addView(TextView(this).apply {
                text = comment
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            })
        }*/
    }
}