package com.trivago.ui.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.trivago.R

class CharacterDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
    }

    companion object {

        fun createIntent(context: Context, cardName: String): Intent {
            return Intent(context, CharacterDetailsActivity::class.java).apply {
                putExtra(CHARACTER_NAME, cardName)
            }
        }

        private const val CHARACTER_NAME = "characterName"
    }
}