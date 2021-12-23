package com.zaelani.moviecatalogue.ui.home.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zaelani.moviecatalogue.R
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.databinding.ItemsMovieBinding
import com.zaelani.moviecatalogue.ui.detail.tvshowdetail.TvShowDetailActivity
import com.zaelani.moviecatalogue.utils.NetworkInfo.IMAGE_URL

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>()  {
    private var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvShowViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemRelease.text = itemView.resources.getString(R.string.release, tvShow.firstAirDate)
                tvItemScore.text = itemView.resources.getString(R.string.user_score, tvShow.voteAverage.toString())
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, tvShow)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(IMAGE_URL + tvShow.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}