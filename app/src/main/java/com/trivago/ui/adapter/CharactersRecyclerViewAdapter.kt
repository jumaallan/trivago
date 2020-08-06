package com.trivago.ui.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trivago.R
import com.trivago.core.data.models.StarWarsCharacter
import com.trivago.databinding.ItemCharacterBinding

typealias CharacterClickListener = (StarWarsCharacter) -> Unit

/**
 * CharactersRecyclerViewAdapter
 *
 * This adapter is responsible for setting the character results list on the character search view
 */
internal class CharactersRecyclerViewAdapter(
    private val listener: CharacterClickListener
) : ListAdapter<StarWarsCharacter, CharactersRecyclerViewAdapter.ViewHolder>(CharactersDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = itemView.context

        fun bind(character: StarWarsCharacter, listener: CharacterClickListener) {
            binding.character = character
            var initials = character.name.splitToSequence(" ")
                .map {
                    it[0].toUpperCase()
                }.joinToString(separator = "", truncated = "")

            if (initials.length > 2) {
                initials = "${initials.first()}${initials.last()}"
            }

            binding.textViewCharacterInitial.text = initials

            val initialsDrawable = binding.textViewCharacterInitial.background as GradientDrawable
            initialsDrawable.setColor(getCharacterBackground(initials))
            binding.textViewCharacterInitial.background = initialsDrawable

            binding.executePendingBindings()
            itemView.setOnClickListener {
                listener.invoke(character)
            }
        }

        private fun getCharacterBackground(initials: String): Int {
            val colorResource = when (initials.first()) {
                'A', 'B', 'C', 'D', 'E' -> R.color.group1
                'F', 'G', 'H', 'I', 'J' -> R.color.group2
                'K', 'L', 'M', 'N', 'O' -> R.color.group3
                'P', 'Q', 'R', 'S', 'T' -> R.color.group4
                'U', 'V', 'W', 'X', 'Y', 'Z' -> R.color.group5
                else -> R.color.group6
            }
            return ContextCompat.getColor(context, colorResource)
        }
    }

    companion object CharactersDiffer : DiffUtil.ItemCallback<StarWarsCharacter>() {
        override fun areItemsTheSame(
            oldItem: StarWarsCharacter,
            newItem: StarWarsCharacter
        ): Boolean = oldItem.url == newItem.url

        override fun areContentsTheSame(
            oldItem: StarWarsCharacter,
            newItem: StarWarsCharacter
        ): Boolean = oldItem == newItem
    }
}