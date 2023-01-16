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
import com.serhat.moviedatabase.data.model.movie.MovieSummary
import com.serhat.moviedatabase.databinding.CardMovieBinding
import com.serhat.moviedatabase.ui.view.activity.MainActivity.Companion.loadImage
import com.serhat.moviedatabase.ui.view.fragment.MovieDetailsFragmentDirections
import com.serhat.moviedatabase.ui.view.fragment.MoviesFragmentDirections
import java.util.*

class MovieAdapter(private val movies: ArrayList<MovieSummary>, private val sourceFragment: Fragments): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val binding: CardMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: CardMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.card_movie, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.adapterMovie = this
        holder.binding.movie = movie

        movie.poster_path?.let {
            holder.binding.cardMovieImgPoster.loadImage(it)
        }
    }

    fun navToMovieDetails(view: View, movie_id: Int) {
        if (sourceFragment == Fragments.MOVIES) {
            val moviesToMovieDetails: MoviesFragmentDirections.MoviesToMovieDetails = MoviesFragmentDirections.moviesToMovieDetails()
            moviesToMovieDetails.movieId = movie_id

            Navigation.findNavController(view).navigate(moviesToMovieDetails)
        } else if (sourceFragment == Fragments.MOVIE_DETAILS) {
            val movieDetailsToMovieDetails: MovieDetailsFragmentDirections.MovieDetailsToMovieDetails = MovieDetailsFragmentDirections.movieDetailsToMovieDetails()
            movieDetailsToMovieDetails.movieId = movie_id

            Navigation.findNavController(view).navigate(movieDetailsToMovieDetails)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(newMovies: List<MovieSummary>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMovies(newMovies: List<MovieSummary>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}