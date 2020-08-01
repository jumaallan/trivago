package com.trivago.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trivago.core.data.models.CharacterResponse
import com.trivago.databinding.ItemCharacterBinding

object CharactersDiffer : DiffUtil.ItemCallback<CharacterResponse>() {
    override fun areItemsTheSame(oldItem: CharacterResponse, newItem: CharacterResponse): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: CharacterResponse,
        newItem: CharacterResponse
    ): Boolean = oldItem == newItem
}

typealias CharacterClickListener = (CharacterResponse) -> Unit

internal class CharactersRecyclerViewAdapter(
    private val listener: CharacterClickListener
) : ListAdapter<CharacterResponse, CharactersRecyclerViewAdapter.ViewHolder>(CharactersDiffer) {

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

        fun bind(character: CharacterResponse, listener: CharacterClickListener) {
            binding.character = character
            binding.executePendingBindings()
            itemView.setOnClickListener {
                listener.invoke(character)
            }
        }
    }
}