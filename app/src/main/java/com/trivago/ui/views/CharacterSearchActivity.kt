package com.trivago.ui.views

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.trivago.R
import com.trivago.databinding.ActivityCharacterDetailsBinding
import com.trivago.ui.viewmodel.CharacterSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchActivity : BaseActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding

    private val characterSearchViewModel: CharacterSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_search)
        binding.lifecycleOwner = this
        binding.characterSearchViewModel = characterSearchViewModel
    }
}