package com.zaelani.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.moviecatalogue.databinding.FragmentTvShowBinding
import com.zaelani.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {
    private lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var favTvShowAdapter: FavoriteTvShowAdapter

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    val fragmentTvShowBinding get() = _fragmentTvShowBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)
            favTvShowAdapter = FavoriteTvShowAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
            viewModel.getFavTvShows().observe(viewLifecycleOwner, {favTvShow ->
                showProgressBar(false)
                favTvShowAdapter.submitList(favTvShow)
                favTvShowAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favTvShowAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavTvShows().observe(viewLifecycleOwner, {favTvShow ->
            if (favTvShow != null){
                favTvShowAdapter.submitList(favTvShow)
                favTvShowAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvShowBinding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}