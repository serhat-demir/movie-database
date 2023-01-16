package com.serhat.moviedatabase.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.serhat.moviedatabase.R
import com.serhat.moviedatabase.data.model.Fragments
import com.serhat.moviedatabase.data.model.tvshow.TvShowSummary
import com.serhat.moviedatabase.databinding.CardTvShowBinding
import com.serhat.moviedatabase.ui.view.activity.MainActivity.Companion.loadImage
import com.serhat.moviedatabase.ui.view.fragment.TvShowDetailsFragmentDirections
import com.serhat.moviedatabase.ui.view.fragment.TvShowsFragmentDirections

class TvShowAdapter(private val tvShows: ArrayList<TvShowSummary>, private val sourceFragment: Fragments): RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    class TvShowViewHolder(val binding: CardTvShowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding: CardTvShowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_tv_show, parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShows[position]

        holder.binding.adapterTvShow = this
        holder.binding.tvShow = tvShow

        tvShow.poster_path?.let {
            holder.binding.cardTvShowImgPoster.loadImage(it)
        }
    }

    fun navToTvShowDetails(view: View, tv_id: Int) {
        if (sourceFragment == Fragments.TV_SHOWS) {
            val tvShowsToTvShowDetails: TvShowsFragmentDirections.TvShowsToTvShowDetails = TvShowsFragmentDirections.tvShowsToTvShowDetails()
            tvShowsToTvShowDetails.tvId = tv_id

            Navigation.findNavController(view).navigate(tvShowsToTvShowDetails)
        } else if (sourceFragment == Fragments.TV_SHOW_DETAILS) {
            val tvShowDetailsToTvShowDetails: TvShowDetailsFragmentDirections.TvShowDetailsToTvShowDetails = TvShowDetailsFragmentDirections.tvShowDetailsToTvShowDetails()
            tvShowDetailsToTvShowDetails.tvId = tv_id

            Navigation.findNavController(view).navigate(tvShowDetailsToTvShowDetails)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTvShows(newTvShows: List<TvShowSummary>) {
        tvShows.clear()
        tvShows.addAll(newTvShows)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTvShows(newTvShows: List<TvShowSummary>) {
        tvShows.addAll(newTvShows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}