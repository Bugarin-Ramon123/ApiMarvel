package com.rbugarin.marvelapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.databinding.CardComicsBinding
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.comics.ComicView

const val CARD_COMIC = 0

class ComicsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listComics = mutableListOf<ComicView>()
    private var hasNextComics = false
    private var onBottomReachedListener: OnBottomReachedListener? = null

    fun addResults(data: List<ComicView>, hasNextComics: Boolean) {
        listComics.addAll(data);
        this.hasNextComics = hasNextComics
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    override fun getItemViewType(position: Int): Int =
        if (position == listComics.size) {
            CIRCULAR_LOADING
        } else {
            CARD_COMIC
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        if (viewType == CARD_COMIC) {
            val cardDataBinding = CardComicsBinding.inflate(layoutInflater, parent, false)
            return ComicViewHolder(cardDataBinding)
        } else {
            val circularLoading = layoutInflater.inflate(R.layout.circular_loading_character, parent, false)
            return CircularLoadingViewHolder(circularLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ComicViewHolder) {
            val result = listComics[position];
            holder.onBind(result);
        } else {
            onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        val increaseSize = if (hasNextComics) 1 else 0
        return listComics.size + increaseSize;
    }

    inner class CircularLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    inner class ComicViewHolder(private val cardComicsBinding: CardComicsBinding)
        : RecyclerView.ViewHolder(cardComicsBinding.root) {

        fun onBind(comicView: ComicView) {
            cardComicsBinding.comic = comicView
            cardComicsBinding.executePendingBindings()
        }

    }
}