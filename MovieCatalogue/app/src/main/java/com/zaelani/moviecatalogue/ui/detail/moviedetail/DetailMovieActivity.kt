package com.zaelani.moviecatalogue.ui.detail.moviedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.zaelani.moviecatalogue.R
import com.zaelani.moviecatalogue.data.source.local.entity.MovieEntity
import com.zaelani.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.zaelani.moviecatalogue.databinding.ContentDetailMovieBinding
import com.zaelani.moviecatalogue.utils.EspressoIdlingResource
import com.zaelani.moviecatalogue.utils.NetworkInfo.IMAGE_URL
import com.zaelani.moviecatalogue.viewmodel.ViewModelFactory


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent
        setContentView(activityDetailMovieBinding.root)
        supportActionBar?.title = "Detail Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE) as MovieEntity

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        viewModel.setSelectedMovie(movie)
        viewModel.getMovie().observe(this, { movie ->
            populateMovie(movie)
        })
        populateMovie(movie)

        contentDetailMovieBinding.apply {
            if(movie.isFav){
                val deleteIcon: Drawable = btnFavMovie.getContext().getResources().getDrawable(R.drawable.ic_delete_forever_24)
                btnFavMovie.setCompoundDrawablesWithIntrinsicBounds(deleteIcon, null, null, null)
                btnFavMovie.text = getString(R.string.delete_favorite)
                btnFavMovie.setOnClickListener {
                    viewModel.deleteFavoriteMovie(movie)
                    Snackbar.make(this.root, "Dihapus dari favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    EspressoIdlingResource.increment()
                    Handler(Looper.getMainLooper()).postDelayed({
                        movie.isFav = false
                        val intent = intent
                        intent.putExtra(EXTRA_MOVIE, movie)
                        finish()
                        startActivity(intent)
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }
                    }, 2000)
                }
            }else{
                val favIcon: Drawable = btnFavMovie.getContext().getResources().getDrawable(R.drawable.ic_favorite_24)
                btnFavMovie.setCompoundDrawablesWithIntrinsicBounds(favIcon, null, null, null)
                btnFavMovie.text = getString(R.string.add_favorite)
                btnFavMovie.setOnClickListener {
                    viewModel.setFavoriteMovie(movie)
                    Snackbar.make(this.root, "Ditambahkan ke favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    EspressoIdlingResource.increment()
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = intent
                        intent.putExtra(EXTRA_MOVIE, movie)
                        finish()
                        startActivity(intent)
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }
                    }, 2000)
                }
            }
        }
    }

    private fun populateMovie(movie: MovieEntity) {
        contentDetailMovieBinding.apply {
            textTitle.text = movie.title
            textRelease.text = movie.releaseDate
            textMovieOverview.text = movie.overview
            textMovieScore.text = getString(R.string.user_score, movie.voteAverage.toString())
            textMoviePopularity.text = getString(R.string.popularity, movie.popularity.toString())
        }
        Glide.with(this)
            .load(IMAGE_URL + movie.posterPath)
            .transform(RoundedCorners(20))
            .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
            .into(contentDetailMovieBinding.imagePoster)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}