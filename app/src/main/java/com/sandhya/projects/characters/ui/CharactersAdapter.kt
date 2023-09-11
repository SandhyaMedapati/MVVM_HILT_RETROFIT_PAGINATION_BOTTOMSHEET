package com.sandhya.projects.characters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sandhya.projects.R
import com.sandhya.projects.characters.data.CharactersData
import com.sandhya.projects.databinding.FragmentStaffListItemBinding
import javax.inject.Inject

class CharactersAdapter @Inject constructor() :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    var charactersList = mutableListOf<CharactersData>()
    var onItemClick: ((CharactersData) -> Unit)? = null

    fun setCharacters(characters: List<CharactersData>) {
        this.charactersList = characters.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding =
            FragmentStaffListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characterItem = charactersList[position]
        holder.bind(characterItem)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    inner class CharactersViewHolder(private val binding: FragmentStaffListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(charactersData: CharactersData) {

            Glide.with(itemView)
                .load(charactersData.image)
                .placeholder(R.drawable.default_image)
                .fitCenter()
                .into(binding.imageView)

            binding.tvName.text = charactersData.name
            binding.tvActorName.text = charactersData.actor
            binding.tvDob.text = charactersData.dateOfBirth

            itemView.setOnClickListener {
                onItemClick?.invoke(charactersData)
            }
        }
    }
}
