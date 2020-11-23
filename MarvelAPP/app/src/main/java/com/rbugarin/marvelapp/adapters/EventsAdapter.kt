package com.rbugarin.marvelapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.databinding.CardEventsBinding
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.events.EventView

const val CARD_EVENT = 0

class EventsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listEvents = mutableListOf<EventView>()
    private var hasNextEvents = false
    private var onBottomReachedListener: OnBottomReachedListener? = null

    fun addResults(data: List<EventView>, hasNextEvents: Boolean) {
        listEvents.addAll(data);
        this.hasNextEvents = hasNextEvents
    }

    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }

    override fun getItemViewType(position: Int): Int =
        if (position == listEvents.size) {
            CIRCULAR_LOADING
        } else {
            CARD_EVENT
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        if (viewType == CARD_EVENT) {
            val cardDataBinding = CardEventsBinding.inflate(layoutInflater, parent, false)
            return EventViewHolder(cardDataBinding)
        } else {
            val circularLoading = layoutInflater.inflate(R.layout.circular_loading_character, parent, false)
            return CircularLoadingViewHolder(circularLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EventViewHolder) {
            val result = listEvents[position];
            holder.onBind(result);
        } else {
            onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        val increaseSize = if (hasNextEvents) 1 else 0
        return listEvents.size + increaseSize;
    }

    inner class CircularLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    inner class EventViewHolder(private val cardEventsBinding: CardEventsBinding)
        : RecyclerView.ViewHolder(cardEventsBinding.root) {

        fun onBind(eventView: EventView) {
            cardEventsBinding.event = eventView
            cardEventsBinding.executePendingBindings()
        }

    }
}
