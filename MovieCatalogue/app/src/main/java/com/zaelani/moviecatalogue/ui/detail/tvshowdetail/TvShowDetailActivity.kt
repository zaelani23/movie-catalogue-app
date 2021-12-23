package com.zaelani.moviecatalogue.ui.detail.tvshowdetail

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
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.databinding.ActivityTvShowDetailBinding
import com.zaelani.moviecatalogue.databinding.ContentDetailTvShowBinding
import com.zaelani.moviecatalogue.utils.EspressoIdlingResource
import com.zaelani.moviecatalogue.utils.NetworkInfo
import com.zaelani.moviecatalogue.viewmodel.ViewModelFactory

class TvShowDetailActivity : AppCompatActivity() {
    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityTvShowDetailBinding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityTvShowDetailBinding.detailContent
        setContentView(activityTvShowDetailBinding.root)
        supportActionBar?.title = "Detail TV Show"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvShow = intent.getParcelableExtra<TvShowEntity>(EXTRA_TV_SHOW) as TvShowEntity

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]
        viewModel.setSelectedTvShow(tvShow)
        viewModel.getTvShow().observe(this, {tvShow ->
            populateTvShow(tvShow)
        })
        populateTvShow(tvShow)

        contentDetailTvShowBinding.apply {
            if(tvShow.isFav){
                val deleteIcon: Drawable = btnFavTvShow.getContext().getResources().getDrawable(R.drawable.ic_delete_forever_24)
                btnFavTvShow.setCompoundDrawablesWithIntrinsicBounds(deleteIcon, null, null, null)
                btnFavTvShow.text = getString(R.string.delete_favorite)
                btnFavTvShow.setOnClickListener {
                    viewModel.deleteFavoriteTvShow(tvShow)
                    Snackbar.make(this.root, "Dihapus dari favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    EspressoIdlingResource.increment()
                    Handler(Looper.getMainLooper()).postDelayed({
                        tvShow.isFav = false
                        val intent = intent
                        intent.putExtra(EXTRA_TV_SHOW, tvShow)
                        finish()
                        startActivity(intent)
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                            EspressoIdlingResource.decrement()
                        }
                    }, 2000)
                }
            }else{
                val favIcon: Drawable = btnFavTvShow.getContext().getResources().getDrawable(R.drawable.ic_favorite_24)
                btnFavTvShow.setCompoundDrawablesWithIntrinsicBounds(favIcon, null, null, null)
                btnFavTvShow.text = getString(R.string.add_favorite)
                btnFavTvShow.setOnClickListener {
                    viewModel.setFavoriteTvShow(tvShow)
                    Snackbar.make(this.root, "Ditambahkan ke favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    EspressoIdlingResource.increment()
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = intent
                        intent.putExtra(EXTRA_TV_SHOW, tvShow)
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

    private fun populateTvShow(tvShow: TvShowEntity) {
        contentDetailTvShowBinding.apply {
            textTitle.text = tvShow.name
            textRelease.text = tvShow.firstAirDate
            textTvShowOverview.text = tvShow.overview
            textTvShowScore.text = getString(R.string.user_score, tvShow.voteAverage.toString())
            textTvShowPopularity.text = getString(R.string.popularity, tvShow.popularity.toString())
        }
        Glide.with(this)
            .load(NetworkInfo.IMAGE_URL + tvShow.posterPath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(contentDetailTvShowBinding.imagePoster)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
}