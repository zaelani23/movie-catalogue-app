package com.zaelani.moviecatalogue.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaelani.moviecatalogue.databinding.FragmentMovieBinding
import com.zaelani.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
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

            val movieAdapter = MovieAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                showProgressBar(false)
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}