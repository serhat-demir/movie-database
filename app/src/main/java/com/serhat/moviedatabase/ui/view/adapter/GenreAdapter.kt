package com.serhat.moviedatabase.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.serhat.moviedatabase.R
import com.serhat.moviedatabase.data.model.Genre
import com.serhat.moviedatabase.databinding.CardGenreBinding

class GenreAdapter(private val genres: List<Genre>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    class GenreViewHolder(val binding: CardGenreBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding: CardGenreBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_genre, parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.genre = genres[position]
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}