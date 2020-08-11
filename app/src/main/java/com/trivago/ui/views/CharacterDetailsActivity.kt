package com.trivago.ui.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.trivago.R
import com.trivago.core.data.models.Film
import com.trivago.core.data.models.Species
import com.trivago.core.utils.convertToInches
import com.trivago.core.utils.hide
import com.trivago.core.utils.show
import com.trivago.databinding.ActivityCharacterDetailsBinding
import com.trivago.ui.adapter.CharacterFilmsRecyclerViewAdapter
import com.trivago.ui.adapter.CharacterSpeciesRecyclerViewAdapter
import com.trivago.ui.viewmodel.CharacterDetailsViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BindingActivity<ActivityCharacterDetailsBinding>() {

    private lateinit var characterFilmsRecyclerViewAdapter: CharacterFilmsRecyclerViewAdapter
    private lateinit var characterSpeciesRecyclerViewAdapter: CharacterSpeciesRecyclerViewAdapter
    private val characterDetailsViewModel: CharacterDetailsViewModel by viewModel()

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        getCharacterDetails()

        title = characterName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // pass to character details layout
        binding.birthYear = characterBirthYear
        binding.heightInCm = characterHeight
        binding.heightInInches = convertToInches(characterHeight.toString())

        characterSpeciesRecyclerViewAdapter = CharacterSpeciesRecyclerViewAdapter()
        binding.recyclerViewSpecies.adapter =
            characterSpeciesRecyclerViewAdapter

        val layoutManagerStats = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCharacterFilms.layoutManager = layoutManagerStats
        val snapHelper = PagerSnapHelper()
        binding.recyclerViewCharacterFilms.onFlingListener = null
        snapHelper.attachToRecyclerView(binding.recyclerViewCharacterFilms)

        characterFilmsRecyclerViewAdapter = CharacterFilmsRecyclerViewAdapter()
        binding.recyclerViewCharacterFilms.adapter = characterFilmsRecyclerViewAdapter

        binding.indicator.attachToRecyclerView(binding.recyclerViewCharacterFilms, snapHelper)
        characterFilmsRecyclerViewAdapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)

        binding.lifecycleOwner = this

        characterDetailsViewModel.characterResponseState.observe(
            this,
            Observer {
                // pass the species list to rv adapter
                setUpSpecies(it.species)

                // pass the planet to the planet view
                binding.planet = it.planet

                // pass the film list to rv adapter
                setUpFilms(it.films)
            }
        )
    }

    @FlowPreview
    private fun getCharacterDetails() {
        characterDetailsViewModel.getCharacterDetails(characterUrl.toString())
    }

    private fun setUpSpecies(characterSpeciesList: List<Species>?) {
        if (characterSpeciesList.isNullOrEmpty()) {
            binding.recyclerViewSpecies.hide()
            binding.headerView.hide()
            binding.emptyView.show()
        } else {
            binding.recyclerViewSpecies.show()
            binding.headerView.show()
            binding.emptyView.hide()
            characterSpeciesRecyclerViewAdapter.submitList(characterSpeciesList)
        }
    }

    private fun setUpFilms(characterFilmsList: List<Film>?) {
        if (characterFilmsList.isNullOrEmpty()) {
            binding.recyclerViewCharacterFilms.hide()
            // we can show some UI here - like nothing to show
        } else {
            binding.recyclerViewCharacterFilms.show()
            binding.filmsProgressBar.hide()
            characterFilmsRecyclerViewAdapter.submitList(characterFilmsList)
        }
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

    override val layoutResId: Int
        get() = R.layout.activity_character_details
}