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
import com.serhat.moviedatabase.data.model.tvshow.TvShows
import com.serhat.moviedatabase.databinding.FragmentTvShowsBinding
import com.serhat.moviedatabase.ui.view.adapter.TvShowAdapter
import com.serhat.moviedatabase.ui.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TvShowsFragment : Fragment() {
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var viewModel: TvShowsViewModel

    private val adapterTvShow: TvShowAdapter = TvShowAdapter(ArrayList(), Fragments.TV_SHOWS)

    private var tvShowListType: TvShows = TvShows.POPULAR
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TvShowsViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_shows, container, false)
        binding.fragmentTvShows = this
        binding.adapterTvShow = adapterTvShow

        initRvScrollListener()
        initObservers()
        initSpinner()
        return binding.root
    }

    fun searchTvShow(searchQuery: String) {
        if (searchQuery.isNotEmpty()) {
            tvShowListType = TvShows.SEARCH
            viewModel.currentPage.value = 1
            this.searchQuery = searchQuery

            loadTvShowList()
        } else Toast.makeText(requireContext(), getString(R.string.msg_search_query_cant_be_empty), Toast.LENGTH_SHORT).show()
    }

    private fun loadTvShowList() {
        val language = Locale.getDefault().toLanguageTag()

        when(tvShowListType) {
            TvShows.POPULAR -> viewModel.getPopularTvShows(language, viewModel.currentPage.value ?: 1)
            TvShows.TOP_RATED -> viewModel.getTopRatedTvShows(language, viewModel.currentPage.value ?: 1)
            TvShows.ON_THE_AIR -> viewModel.getTvShowsOnTheAir(language, viewModel.currentPage.value ?: 1)
            TvShows.SEARCH -> viewModel.searchTvShows(language, searchQuery, viewModel.currentPage.value ?: 1)
        }
    }

    private fun initRvScrollListener() {
        binding.frgTvShowsNestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight && (viewModel.currentPage.value ?: 1) < (viewModel.totalPages.value ?: 1)) {
                viewModel.currentPage.value = (viewModel.currentPage.value ?: 1) + 1
                binding.showProgressBar = true
                loadTvShowList()
            }
        })
    }

    private fun initObservers() {
        viewModel.tvShows.observe(viewLifecycleOwner) {
            it?.let {
                if ((viewModel.currentPage.value ?: 1) == 1) adapterTvShow.setTvShows(it)
                else adapterTvShow.addTvShows(it)

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
        binding.frgTvShowsSpSort.setOnSpinnerItemSelectedListener<String> {_, _, newIndex, _ ->
            when(newIndex) {
                0 -> tvShowListType = TvShows.POPULAR
                1 -> tvShowListType = TvShows.TOP_RATED
                2 -> tvShowListType = TvShows.ON_THE_AIR
            }

            viewModel.currentPage.value = 1
            loadTvShowList()
        }

        binding.frgTvShowsSpSort.selectItemByIndex(0)
    }
}