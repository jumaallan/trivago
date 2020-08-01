package com.trivago.ui.views

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.trivago.R
import com.trivago.core.data.models.CharacterResponse
import com.trivago.core.utils.hide
import com.trivago.core.utils.show
import com.trivago.databinding.ActivityCharacterSearchBinding
import com.trivago.ui.adapter.CharactersRecyclerViewAdapter
import com.trivago.ui.viewmodel.CharacterSearchViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchActivity : BaseActivity() {

    private lateinit var binding: ActivityCharacterSearchBinding
    private lateinit var charactersRecyclerViewAdapter: CharactersRecyclerViewAdapter
    private val characterSearchViewModel: CharacterSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_search)
        binding.lifecycleOwner = this
        binding.characterSearchViewModel = characterSearchViewModel

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                // characterSearchViewModel.searchStarWarsCharacters(query)

            }
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

        lifecycleScope.launch {
            characterSearchViewModel.searchStarWarsCharacters("Da")
                .onEach { setUpViews(it) }
                .launchIn(lifecycleScope)
        }
    }

    private fun setUpViews(charactersList: List<CharacterResponse>) {
        if (charactersList.isNullOrEmpty()) {
            binding.recyclerViewCharacters.hide()
            // we can show some UI here - like nothing to show
        } else {
            binding.recyclerViewCharacters.show()
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
        }

        return true
    }
}