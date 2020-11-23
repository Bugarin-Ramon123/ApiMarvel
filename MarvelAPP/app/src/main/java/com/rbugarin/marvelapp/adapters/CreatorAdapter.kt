package com.rbugarin.marvelapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.databinding.CardAllCharactersBinding
import com.rbugarin.marvelapp.databinding.CardCreatorsBinding
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.characters.CharacterView
import com.rbugarin.marvelapp.models.creators.CreatorView

//const val CIRCULAR_LOADING = 1
const val CARD_CREATOR = 0

class CreatorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listCreators = mutableListOf<CreatorView>()
    private var hasNextCreators = false
    private var onBottomReachedListener: OnBottomReachedListener? = null

    fun addResults(data: List<CreatorView>, hasNextCreators: Boolean) {
        listCreators.addAll(data);
        this.hasNextCreators = hasNextCreators
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    override fun getItemViewType(position: Int): Int =
        if (position == listCreators.size) {
            CIRCULAR_LOADING
        } else {
            CARD_CREATOR
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        if (viewType == CARD_CREATOR) {
            val cardDataBinding = CardCreatorsBinding.inflate(layoutInflater, parent, false)
            return CreatorViewHolder(cardDataBinding)
        } else {
            val circularLoading = layoutInflater.inflate(R.layout.circular_loading_character, parent, false)
            return CircularLoadingViewHolder(circularLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            val result = listCreators[position];
            holder.onBind(result);
        } else {
            onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        val increaseSize = if (hasNextCreators) 1 else 0
        return listCreators.size + increaseSize;
    }

    inner class CircularLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    inner class CreatorViewHolder(private val cardCreatorsBinding: CardCreatorsBinding)
        : RecyclerView.ViewHolder(cardCreatorsBinding.root) {

        fun onBind(creatorView: CreatorView) {
            cardCreatorsBinding.creator = creatorView
            cardCreatorsBinding.executePendingBindings()
        }

    }
}