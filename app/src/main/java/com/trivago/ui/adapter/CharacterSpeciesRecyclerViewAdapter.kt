package com.trivago.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trivago.core.data.models.Species
import com.trivago.databinding.ItemSpeciesBinding

/**
 * CharacterSpeciesRecyclerViewAdapter
 *
 * This adapter is responsible for setting the character species list on the character details view
 */
internal class CharacterSpeciesRecyclerViewAdapter :
    ListAdapter<Species, CharacterSpeciesRecyclerViewAdapter.ViewHolder>(
        CharacterFilmsDiffer
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSpeciesBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemSpeciesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(species: Species) {
            binding.species = species
            binding.executePendingBindings()
        }
    }

    companion object CharacterFilmsDiffer : DiffUtil.ItemCallback<Species>() {
        override fun areItemsTheSame(
            oldItem: Species,
            newItem: Species
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Species,
            newItem: Species
        ): Boolean = oldItem == newItem
    }
}