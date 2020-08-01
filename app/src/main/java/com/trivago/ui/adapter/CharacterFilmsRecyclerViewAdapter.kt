package com.trivago.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trivago.core.data.network.FilmDetailResponse
import com.trivago.databinding.ItemFilmBinding

object CharacterFilmsDiffer : DiffUtil.ItemCallback<FilmDetailResponse>() {
    override fun areItemsTheSame(
        oldItem: FilmDetailResponse,
        newItem: FilmDetailResponse
    ): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: FilmDetailResponse,
        newItem: FilmDetailResponse
    ): Boolean = oldItem == newItem
}

internal class CharacterFilmsRecyclerViewAdapter :
    ListAdapter<FilmDetailResponse, CharacterFilmsRecyclerViewAdapter.ViewHolder>(
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

        fun bind(filmDetailResponse: FilmDetailResponse) {
            binding.film = filmDetailResponse
            binding.executePendingBindings()
        }
    }
}