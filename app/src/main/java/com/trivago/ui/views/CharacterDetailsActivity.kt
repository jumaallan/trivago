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
        binding.lifecycleOwner = this

    }

    private val characterName get() = intent.getStringExtra(CHARACTER_NAME)
    private val characterUrl get() = intent.getStringExtra(CHARACTER_URL)

    companion object {

        fun createIntent(context: Context, characterName: String, characterUrl: String): Intent {
            return Intent(context, CharacterDetailsActivity::class.java).apply {
                putExtra(CHARACTER_NAME, characterName)
                putExtra(CHARACTER_URL, characterUrl)
            }
        }

        private const val CHARACTER_NAME = "characterName"
        private const val CHARACTER_URL = "characterUrl"
    }
}