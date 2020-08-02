package com.trivago.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

        fun bind(character: StarWarsCharacter, listener: CharacterClickListener) {
            binding.character = character
            binding.executePendingBindings()
            itemView.setOnClickListener {
                listener.invoke(character)
            }
        }
    }

    companion object CharactersDiffer : DiffUtil.ItemCallback<StarWarsCharacter>() {
        override fun areItemsTheSame(
            oldItem: StarWarsCharacter,
            newItem: StarWarsCharacter
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: StarWarsCharacter,
            newItem: StarWarsCharacter
        ): Boolean = oldItem == newItem
    }
}