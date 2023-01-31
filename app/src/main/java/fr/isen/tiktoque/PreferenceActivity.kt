package fr.isen.tiktoque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
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

        binding.checkBoxType1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 1")
            } else {
                selectedTypes.remove("Type 1")
            }
        }

        binding.checkBoxType2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 2")
            } else {
                selectedTypes.remove("Type 2")
            }
        }

        binding.checkBoxType3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 3")
            } else {
                selectedTypes.remove("Type 3")
            }
        }

        binding.checkBoxType4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 4")
            } else {
                selectedTypes.remove("Type 4")
            }
        }

        binding.checkBoxType5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 5")
            } else {
                selectedTypes.remove("Type 5")
            }
        }

        binding.checkBoxType6.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 6")
            } else {
                selectedTypes.remove("Type 6")
            }
        }

        binding.checkBoxType7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 7")
            } else {
                selectedTypes.remove("Type 7")
            }
        }

        binding.checkBoxType8.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedTypes.add("Type 8")
            } else {
                selectedTypes.remove("Type 8")
            }
        }

        binding.buttonSave.setOnClickListener {
            saveToDatabase(selectedTypes)
        }
    }
    private fun saveToDatabase(selectedTypes: List<String>) {
        // Code pour enregistrer les types de nourriture sélectionnés dans la base de données
    }
}
