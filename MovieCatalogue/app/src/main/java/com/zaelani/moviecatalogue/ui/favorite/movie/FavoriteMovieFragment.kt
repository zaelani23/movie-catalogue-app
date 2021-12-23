package com.zaelani.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.moviecatalogue.databinding.FragmentMovieBinding
import com.zaelani.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var favMovieAdapter: FavoriteMovieAdapter
    private lateinit var viewModel: FavoriteMovieViewModel

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    val fragmentMovieBinding get() = _fragmentMovieBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)
            favMovieAdapter = FavoriteMovieAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            viewModel.getFavMovies().observe(viewLifecycleOwner, { favMovies ->
                showProgressBar(false)
                favMovieAdapter.submitList(favMovies)
                favMovieAdapter.notifyDataSetChanged()
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMovieAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavMovies().observe(viewLifecycleOwner, { favMovies ->
            if (favMovies != null) {
                favMovieAdapter.submitList(favMovies)
                favMovieAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}