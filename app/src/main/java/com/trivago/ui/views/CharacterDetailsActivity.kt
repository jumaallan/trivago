package com.trivago.ui.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.trivago.R
import com.trivago.core.data.models.Film
import com.trivago.core.utils.hide
import com.trivago.core.utils.show
import com.trivago.databinding.ActivityCharacterDetailsBinding
import com.trivago.ui.adapter.CharacterFilmsRecyclerViewAdapter
import com.trivago.ui.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding
    private lateinit var characterFilmsRecyclerViewAdapter: CharacterFilmsRecyclerViewAdapter
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

        val layoutManagerStats = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCharacterFilms.layoutManager = layoutManagerStats
        val snapHelper = PagerSnapHelper()
        binding.recyclerViewCharacterFilms.onFlingListener = null
        snapHelper.attachToRecyclerView(binding.recyclerViewCharacterFilms)

        characterFilmsRecyclerViewAdapter = CharacterFilmsRecyclerViewAdapter()
        binding.recyclerViewCharacterFilms.adapter = characterFilmsRecyclerViewAdapter

        binding.indicator.attachToRecyclerView(binding.recyclerViewCharacterFilms, snapHelper)
        characterFilmsRecyclerViewAdapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)

        setUpViews(prepareMockFilms())

        binding.lifecycleOwner = this
    }

    private fun setUpViews(characterFilmsList: List<Film>) {
        if (characterFilmsList.isNullOrEmpty()) {
            binding.recyclerViewCharacterFilms.hide()
            // we can show some UI here - like nothing to show
        } else {
            binding.recyclerViewCharacterFilms.show()
            characterFilmsRecyclerViewAdapter.submitList(characterFilmsList)
        }
    }

    private fun prepareMockFilms(): List<Film> {
        var models = ArrayList<Film>()
        models.add(
            Film(
                "Trivago",
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                        "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                        "        \"technologically advanced and open to imagination and magic.\\n\\n\"\n" +
                        "\n" +
                        "        \"Bold, graphic, intentional."
            )
        )
        models.add(
            Film(
                "Trivago",
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                        "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                        "        \"technologically advanced and open to imagination and magic.\\n\\n\"\n" +
                        "\n" +
                        "        \"Bold, graphic, intentional."
            )
        )
        models.add(
            Film(
                "Trivago",
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                        "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                        "        \"technologically advanced and open to imagination and magic.\\n\\n\"\n" +
                        "\n" +
                        "        \"Bold, graphic, intentional."
            )
        )
        models.add(
            Film(
                "Trivago",
                "A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                        "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                        "        \"technologically advanced and open to imagination and magic.\\n\\n\"\n" +
                        "\n" +
                        "        \"Bold, graphic, intentional."
            )
        )
        return models
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