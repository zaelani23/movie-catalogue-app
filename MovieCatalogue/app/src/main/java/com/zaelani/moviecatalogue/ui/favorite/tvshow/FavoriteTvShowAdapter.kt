package com.zaelani.moviecatalogue.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zaelani.moviecatalogue.R
import com.zaelani.moviecatalogue.data.source.local.entity.TvShowEntity
import com.zaelani.moviecatalogue.databinding.ItemsMovieBinding
import com.zaelani.moviecatalogue.ui.detail.tvshowdetail.TvShowDetailActivity
import com.zaelani.moviecatalogue.utils.NetworkInfo

class FavoriteTvShowAdapter: PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.FavoriteTvShowViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        val itemsTvShowBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowAdapter.FavoriteTvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class FavoriteTvShowViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
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
                        .load(NetworkInfo.IMAGE_URL + tvShow.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}