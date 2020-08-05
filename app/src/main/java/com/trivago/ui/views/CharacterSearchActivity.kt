package com.trivago.ui.views

import android.app.SearchManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.trivago.R
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.core.utils.hide
import com.trivago.core.utils.show
import com.trivago.data.model.Character
import com.trivago.databinding.ActivityCharacterSearchBinding
import com.trivago.ui.adapter.CharactersRecyclerViewAdapter
import com.trivago.ui.viewmodel.CharacterSearchViewModel
import com.trivago.utils.toResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityCharacterSearchBinding
    private lateinit var charactersRecyclerViewAdapter: CharactersRecyclerViewAdapter
    private val characterSearchViewModel: CharacterSearchViewModel by viewModel()

    var adapter: ArrayAdapter<String>? = null
    private val stringSuggestionArray: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_search)
        binding.lifecycleOwner = this
        binding.characterSearchViewModel = characterSearchViewModel

        binding.listView.hide()
        val starWarsCharacters = mutableListOf<StarWarsCharacter>()
        characterSearchViewModel.getCharacters().observe(
            this,
            Observer {
                it.forEach {character ->
                    starWarsCharacters.add(character.toResponse())
                }
                showPreviousSearches(starWarsCharacters)
            }
        )

        binding.listView.onItemClickListener = OnItemClickListener { parent, _, position, _ ->
            val selectedItem =
                parent.getItemAtPosition(position) as String
            searchStarWarsCharacter(selectedItem)
            binding.listView.hide()
        }

        charactersRecyclerViewAdapter = CharactersRecyclerViewAdapter {
            val intent = CharacterDetailsActivity.createIntent(
                context = this,
                characterName = it.name,
                characterUrl = it.url,
                characterBirthYear = it.birthYear,
                characterHeight = it.height
            )
            startActivity(intent)
        }

        binding.recyclerViewCharacters.adapter = charactersRecyclerViewAdapter
    }

    private fun searchStarWarsCharacter(characterName: String) {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                characterSearchViewModel.searchStarWarsCharacters(characterName = characterName)
                    .observe(
                        this@CharacterSearchActivity,
                        Observer {
                            setUpViews(it)
                            saveCharacters(it)
                        }
                    )
            }
        }
    }

    private fun saveCharacters(it: List<StarWarsCharacter>) {
        val characters = mutableListOf<Character>()
        it.forEach {starWarCharacter ->
            characters.add(starWarCharacter.toResponse())
        }
        characterSearchViewModel.saveCharacters(characters)
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
        binding.listView.hide()
        searchStarWarsCharacter(query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        binding.listView.show()
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
}