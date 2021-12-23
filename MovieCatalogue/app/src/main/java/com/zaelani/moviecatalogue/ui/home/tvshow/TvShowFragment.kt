package com.zaelani.moviecatalogue.ui.home.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.moviecatalogue.databinding.FragmentTvShowBinding
import com.zaelani.moviecatalogue.viewmodel.ViewModelFactory

class TVShowFragment : Fragment() {
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

            val tvShowAdapter = TvShowAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
                showProgressBar(false)
                tvShowAdapter.setTvShow(tvShow)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }

    private fun showProgressBar(state: Boolean) {
        fragmentTvShowBinding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}