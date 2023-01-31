package fr.isen.tiktoque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fr.isen.tiktoque.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferenceBinding
    private lateinit var auth : FirebaseAuth
    private val selectedTypes = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.checkBoxType1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("asiatique")
            } else {
                selectedTypes.remove("asiatique")
            }
        }

        binding.checkBoxType2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("espagnole")
            } else {
                selectedTypes.remove("espagnole")
            }
        }

        binding.checkBoxType3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("français")
            } else {
                selectedTypes.remove("français")
            }
        }

        binding.checkBoxType4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("indien")
            } else {
                selectedTypes.remove("indien")
            }
        }

        binding.checkBoxType5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("italien")
            } else {
                selectedTypes.remove("italien")
            }
        }

        binding.checkBoxType6.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("méditerranéen")
            } else {
                selectedTypes.remove("méditerranéen")
            }
        }

        binding.checkBoxType7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("mexicain")
            } else {
                selectedTypes.remove("mexicain")
            }
        }

        binding.checkBoxType8.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("thaï")
            } else {
                selectedTypes.remove("thaï")
            }
        }

        binding.buttonSave.setOnClickListener {
            /*saveToDatabase(selectedTypes)*/
            val intent = Intent(this@PreferenceActivity, FeedActivity::class.java)
            intent.putExtra("selectedTypes", selectedTypes.toTypedArray())
            startActivity(intent)
        }
    }
}
