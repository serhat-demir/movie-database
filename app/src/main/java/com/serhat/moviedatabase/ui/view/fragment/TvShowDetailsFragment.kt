package com.serhat.moviedatabase.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.serhat.moviedatabase.R
import com.serhat.moviedatabase.data.model.Fragments
import com.serhat.moviedatabase.databinding.FragmentTvShowDetailsBinding
import com.serhat.moviedatabase.ui.view.activity.MainActivity.Companion.loadImage
import com.serhat.moviedatabase.ui.view.adapter.GenreAdapter
import com.serhat.moviedatabase.ui.view.adapter.TvShowAdapter
import com.serhat.moviedatabase.ui.viewmodel.TvShowDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TvShowDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowDetailsBinding
    private lateinit var viewModel: TvShowDetailsViewModel

    private val adapterTvShow: TvShowAdapter = TvShowAdapter(ArrayList(), Fragments.TV_SHOW_DETAILS)
    private val language: String = Locale.getDefault().toLanguageTag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TvShowDetailsViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show_details, container, false)
        binding.fragmentTvShowDetails = this
        binding.adapterTvShow = adapterTvShow

        val bundle: TvShowDetailsFragmentArgs by navArgs()
        initRvScrollListener(bundle.tvId)

        initObservers()
        viewModel.getTvShowDetails(bundle.tvId, language)

        return binding.root
    }

    private fun loadSimilarTvShows(movie_id: Int) {
        viewModel.getSimilarTvShows(movie_id, language, viewModel.currentPage.value ?: 1)
    }

    private fun initRvScrollListener(movie_id: Int) {
        binding.frgTvShowDetailsNestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight && (viewModel.currentPage.value ?: 1) < (viewModel.totalPages.value ?: 1)) {
                viewModel.currentPage.value = (viewModel.currentPage.value ?: 1) + 1
                binding.showProgressBar = true
                loadSimilarTvShows(movie_id)
            }
        })
    }

    private fun initObservers() {
        viewModel.tvShow.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvShow = it
                binding.adapterGenre = GenreAdapter(it.genres)

                it.backdrop_path?.let {it2 ->
                    binding.frgTvShowDetailsImgPoster.loadImage(it2)
                }

                loadSimilarTvShows(it.id)
            } else {
                Toast.makeText(requireContext(), getString(R.string.msg_tv_show_details_couldnt_loaded), Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        viewModel.similarTvShows.observe(viewLifecycleOwner) {
            it?.let {
                if ((viewModel.currentPage.value ?: 1) == 1) adapterTvShow.setTvShows(it)
                else adapterTvShow.setTvShows(it)

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