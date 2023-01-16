package com.serhat.moviedatabase.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.serhat.moviedatabase.R
import com.serhat.moviedatabase.data.model.Fragments
import com.serhat.moviedatabase.databinding.FragmentMovieDetailsBinding
import com.serhat.moviedatabase.ui.view.activity.MainActivity.Companion.loadImage
import com.serhat.moviedatabase.ui.view.adapter.GenreAdapter
import com.serhat.moviedatabase.ui.view.adapter.MovieAdapter
import com.serhat.moviedatabase.ui.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    private val adapterMovie: MovieAdapter = MovieAdapter(ArrayList(), Fragments.MOVIE_DETAILS)
    private val language: String = Locale.getDefault().toLanguageTag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        binding.fragmentMovieDetails = this
        binding.adapterMovie = adapterMovie

        val bundle: MovieDetailsFragmentArgs by navArgs()
        initRvScrollListener(bundle.movieId)

        initObservers()
        viewModel.getMovieDetails(bundle.movieId, language)

        return binding.root
    }

    private fun loadSimilarMovies(movie_id: Int) {
        viewModel.getSimilarMovies(movie_id, language, viewModel.currentPage.value ?: 1)
    }

    private fun initRvScrollListener(movie_id: Int) {
        binding.frgMovieDetailsNestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight && (viewModel.currentPage.value ?: 1) < (viewModel.totalPages.value ?: 1)) {
                viewModel.currentPage.value = (viewModel.currentPage.value ?: 1) + 1
                binding.showProgressBar = true
                loadSimilarMovies(movie_id)
            }
        })
    }

    private fun initObservers() {
        viewModel.movie.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.movie = it
                binding.adapterGenre = GenreAdapter(it.genres)

                it.backdrop_path?.let {it2 ->
                    binding.frgMovieDetailsImgPoster.loadImage(it2)
                }

                loadSimilarMovies(it.id)
            } else {
                Toast.makeText(requireContext(), getString(R.string.msg_movie_details_couldnt_loaded), Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        viewModel.similarMovies.observe(viewLifecycleOwner) {
            it?.let {
                if ((viewModel.currentPage.value ?: 1) == 1) adapterMovie.setMovies(it)
                else adapterMovie.addMovies(it)

                binding.showProgressBar = false
            }
        }

        viewModel.message.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}