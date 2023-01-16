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
import com.serhat.moviedatabase.R
import com.serhat.moviedatabase.data.model.Fragments
import com.serhat.moviedatabase.data.model.movie.Movies
import com.serhat.moviedatabase.databinding.FragmentMoviesBinding
import com.serhat.moviedatabase.ui.view.adapter.MovieAdapter
import com.serhat.moviedatabase.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private val adapterMovie: MovieAdapter = MovieAdapter(ArrayList(), Fragments.MOVIES)

    private var movieListType: Movies = Movies.POPULAR
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        binding.fragmentMovies = this
        binding.adapterMovie = adapterMovie

        initRvScrollListener()
        initObservers()
        initSpinner()
        return binding.root
    }

    fun searchMovie(searchQuery: String) {
        if (searchQuery.isNotEmpty()) {
            movieListType = Movies.SEARCH
            viewModel.currentPage.value = 1
            this.searchQuery = searchQuery

            loadMovieList()
        } else Toast.makeText(requireContext(), getString(R.string.msg_search_query_cant_be_empty), Toast.LENGTH_SHORT).show()
    }

    private fun loadMovieList() {
        val language = Locale.getDefault().toLanguageTag()

        when(movieListType) {
            Movies.POPULAR -> viewModel.getPopularMovies(language, viewModel.currentPage.value ?: 1)
            Movies.TOP_RATED -> viewModel.getTopRatedMovies(language, viewModel.currentPage.value ?: 1)
            Movies.UPCOMING -> viewModel.getUpcomingMovies(language, viewModel.currentPage.value ?: 1)
            Movies.SEARCH -> viewModel.searchMovies(language, searchQuery, viewModel.currentPage.value ?: 1)
        }
    }

    private fun initRvScrollListener() {
        binding.frgMoviesNestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight && (viewModel.currentPage.value ?: 1) < (viewModel.totalPages.value ?: 1)) {
                viewModel.currentPage.value = (viewModel.currentPage.value ?: 1) + 1
                binding.showProgressBar = true
                loadMovieList()
            }
        })
    }

    private fun initObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
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

    private fun initSpinner() {
        binding.frgMoviesSpSort.setOnSpinnerItemSelectedListener<String> {_, _, newIndex, _ ->
            when(newIndex) {
                0 -> movieListType = Movies.POPULAR
                1 -> movieListType = Movies.TOP_RATED
                2 -> movieListType = Movies.UPCOMING
            }

            viewModel.currentPage.value = 1
            loadMovieList()
        }

        binding.frgMoviesSpSort.selectItemByIndex(0)
    }
}