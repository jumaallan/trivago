package com.trivago.ui.views

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CharacterSearchActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityCharacterSearchBinding
    private lateinit var charactersRecyclerViewAdapter: CharactersRecyclerViewAdapter
    private val characterSearchViewModel: CharacterSearchViewModel by viewModel()

    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_search)
        binding.lifecycleOwner = this
        binding.characterSearchViewModel = characterSearchViewModel

        binding.listView.hide()
        characterSearchViewModel.getCharacters().observe(this, Observer { it ->
            val stringSuggestionArray = arrayOf<String>()
            it.forEach {
                stringSuggestionArray.plus(it.name)
                Timber.d("Query response ${it.name}")
            }
            adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, stringSuggestionArray)
            binding.listView.adapter = adapter
        })

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
            withContext(Dispatchers.Main) {
                characterSearchViewModel.searchStarWarsCharacters("Da")
                    .observe(
                        this@CharacterSearchActivity,
                        Observer {
                            setUpViews(it)
                        }
                    )
            }
        }
    }

    private fun setUpViews(charactersList: List<StarWarsCharacter>) {
        if (charactersList.isNullOrEmpty()) {
            binding.recyclerViewCharacters.hide()
            binding.emptyView.show()
        } else {
            binding.recyclerViewCharacters.show()
            binding.emptyView.hide()
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
        Timber.d("Query 1 $query")
        binding.listView.hide()
        characterSearchViewModel.saveCharacter(
            Character(
                query.toString(), "", "", ""
            )
        )
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("onQueryTextChange: newText is %s", newText)
        binding.listView.show()
        adapter?.filter?.filter(newText)
        return true
    }
}