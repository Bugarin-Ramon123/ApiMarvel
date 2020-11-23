package com.rbugarin.marvelapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.databinding.CardStoriesBinding
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.stories.StorieView

const val CARD_STORIE = 0

class StoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listStories = mutableListOf<StorieView>()
    private var hasNextStories = false
    private var onBottomReachedListener: OnBottomReachedListener? = null

    fun addResults(data: List<StorieView>, hasNextStories: Boolean) {
        listStories.addAll(data);
        this.hasNextStories = hasNextStories
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    override fun getItemViewType(position: Int): Int =
        if (position == listStories.size) {
            CIRCULAR_LOADING
        } else {
            CARD_STORIE
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        if (viewType == CARD_STORIE) {
            val cardDataBinding = CardStoriesBinding.inflate(layoutInflater, parent, false)
            return StorieViewHolder(cardDataBinding)
        } else {
            val circularLoading = layoutInflater.inflate(R.layout.circular_loading_character, parent, false)
            return CircularLoadingViewHolder(circularLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StorieViewHolder) {
            val result = listStories[position];
            holder.onBind(result);
        } else {
            onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        val increaseSize = if (hasNextStories) 1 else 0
        return listStories.size + increaseSize;
    }

    inner class CircularLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    inner class StorieViewHolder(private val cardStoriesBinding: CardStoriesBinding)
        : RecyclerView.ViewHolder(cardStoriesBinding.root) {

        fun onBind(storieView: StorieView) {
            cardStoriesBinding.storie = storieView
            cardStoriesBinding.executePendingBindings()
        }

    }
}



