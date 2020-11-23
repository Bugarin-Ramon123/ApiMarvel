package com.rbugarin.marvelapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.databinding.CardSeriesBinding
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.series.SerieView

const val CARD_SERIE = 0

class SeriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listSeries = mutableListOf<SerieView>()
    private var hasNextSeries = false
    private var onBottomReachedListener: OnBottomReachedListener? = null

    fun addResults(data: List<SerieView>, hasNextSeries: Boolean) {
        listSeries.addAll(data);
        this.hasNextSeries = hasNextSeries
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    override fun getItemViewType(position: Int): Int =
        if (position == listSeries.size) {
            CIRCULAR_LOADING
        } else {
            CARD_SERIE
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        if (viewType == CARD_SERIE) {
            val cardDataBinding = CardSeriesBinding.inflate(layoutInflater, parent, false)
            return SerieViewHolder(cardDataBinding)
        } else {
            val circularLoading = layoutInflater.inflate(R.layout.circular_loading_character, parent, false)
            return CircularLoadingViewHolder(circularLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SerieViewHolder) {
            val result = listSeries[position];
            holder.onBind(result);
        } else {
            onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        val increaseSize = if (hasNextSeries) 1 else 0
        return listSeries.size + increaseSize;
    }

    inner class CircularLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    inner class SerieViewHolder(private val cardSeriesBinding: CardSeriesBinding)
        : RecyclerView.ViewHolder(cardSeriesBinding.root) {

        fun onBind(serieView: SerieView) {
            cardSeriesBinding.serie = serieView
            cardSeriesBinding.executePendingBindings()
        }

    }
}

