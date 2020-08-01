package com.trivago.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trivago.core.data.models.Film
import com.trivago.databinding.ItemFilmBinding

internal class CharacterFilmsRecyclerViewAdapter :
    ListAdapter<Film, CharacterFilmsRecyclerViewAdapter.ViewHolder>(
        CharacterFilmsDiffer
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(filmDetailResponse: Film) {
            binding.film = filmDetailResponse
            binding.executePendingBindings()
        }
    }

    companion object CharacterFilmsDiffer : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(
            oldItem: Film,
            newItem: Film
        ): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: Film,
            newItem: Film
        ): Boolean = oldItem == newItem
    }
}