package com.trivago.ui.views

import android.os.Bundle
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

        charactersRecyclerViewAdapter = CharactersRecyclerViewAdapter {
            val intent = CharacterDetailsActivity.createIntent(
                context = this,
                cardName = it.name
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
}