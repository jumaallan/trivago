package com.trivago.ui.views

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.trivago.R
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.utils.hide
import com.trivago.core.utils.show
import com.trivago.databinding.ActivityCharacterSearchBinding
import com.trivago.ui.adapter.CharactersRecyclerViewAdapter
import com.trivago.ui.viewmodel.CharacterSearchViewModel
import com.trivago.utils.toResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchActivity :
    BindingActivity<ActivityCharacterSearchBinding>(),
    SearchView.OnQueryTextListener {

    private lateinit var charactersRecyclerViewAdapter: CharactersRecyclerViewAdapter
    private val characterSearchViewModel: CharacterSearchViewModel by viewModel()

    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.characterSearchViewModel = characterSearchViewModel

        characterSearchViewModel.getCharacters().observe(
            this,
            Observer {
                showPreviousSearches(it.map { character -> character.toResponse() }.asReversed())
            }
        )

        charactersRecyclerViewAdapter = CharactersRecyclerViewAdapter {
            val intent = CharacterDetailsActivity.createIntent(
                context = this,
                characterName = it.name,
                characterUrl = it.url,
                characterBirthYear = it.birthYear,
                characterHeight = it.height
            )
            saveCharacter(it)
            startActivity(intent)
        }

        binding.recyclerViewCharacters.adapter = charactersRecyclerViewAdapter
    }

    private fun searchStarWarsCharacter(characterName: String) {
        characterSearchViewModel.searchStarWarsCharacters(characterName = characterName)
            .observe(
                this@CharacterSearchActivity,
                Observer {
                    setUpViews(it)
                }
            )
    }

    private fun saveCharacter(character: StarWarsCharacter) {
        characterSearchViewModel.saveCharacter(character.toResponse())
    }

    private fun setUpViews(charactersList: List<StarWarsCharacter>) {
        if (charactersList.isNullOrEmpty()) {
            binding.recyclerViewCharacters.hide()
            binding.emptyView.show()
            binding.textViewPreviousLabel.hide()
        } else {
            binding.recyclerViewCharacters.show()
            binding.emptyView.hide()
            binding.textViewPreviousLabel.hide()
            charactersRecyclerViewAdapter.submitList(charactersList)
        }
    }

    private fun showPreviousSearches(charactersList: List<StarWarsCharacter>) {
        if (charactersList.isNullOrEmpty()) {
            binding.recyclerViewCharacters.hide()
            binding.emptyView.show()
            binding.textViewPreviousLabel.hide()
        } else {
            binding.recyclerViewCharacters.show()
            binding.emptyView.hide()
            binding.textViewPreviousLabel.show()
            charactersRecyclerViewAdapter.submitList(charactersList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            setOnQueryTextListener(this@CharacterSearchActivity)
        }

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchStarWarsCharacter(query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        binding.emptyView.hide()
        adapter?.filter?.filter(newText)
        return true
    }

    override fun onBackPressed() {
        // Workaround for https://issuetracker.google.com/issues/139738913
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    override val layoutResId: Int
        get() = R.layout.activity_character_search
}