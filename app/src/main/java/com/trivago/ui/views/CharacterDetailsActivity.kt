package com.trivago.ui.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.trivago.R
import com.trivago.databinding.ActivityCharacterDetailsBinding
import com.trivago.ui.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding
    private val characterDetailsViewModel: CharacterDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details)

        title = characterName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // pass to character details layout
        binding.layoutCharacterDetails.birthYear = characterBirthYear
        binding.layoutCharacterDetails.heightInCm = characterHeight
        binding.layoutCharacterDetails.heightInInches = characterHeight

        // pass to species details layout
        // binding.layoutCharacterSpecies.species =

        // pass to planet details layout
//        binding.layoutCharacterPlanet.planet =

        binding.lifecycleOwner = this
    }

    private val characterName get() = intent.getStringExtra(CHARACTER_NAME)
    private val characterUrl get() = intent.getStringExtra(CHARACTER_URL)
    private val characterBirthYear get() = intent.getStringExtra(CHARACTER_BIRTH_YEAR)
    private val characterHeight get() = intent.getStringExtra(CHARACTER_HEIGHT)

    companion object {

        fun createIntent(
            context: Context,
            characterName: String,
            characterUrl: String,
            characterBirthYear: String,
            characterHeight: String
        ): Intent {
            return Intent(context, CharacterDetailsActivity::class.java).apply {
                putExtra(CHARACTER_NAME, characterName)
                putExtra(CHARACTER_URL, characterUrl)
                putExtra(CHARACTER_BIRTH_YEAR, characterBirthYear)
                putExtra(CHARACTER_HEIGHT, characterHeight)
            }
        }

        private const val CHARACTER_NAME = "characterName"
        private const val CHARACTER_URL = "characterUrl"
        private const val CHARACTER_BIRTH_YEAR = "characterBirthYear"
        private const val CHARACTER_HEIGHT = "characterHeight"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        onBackPressed()
        return true
    }
}