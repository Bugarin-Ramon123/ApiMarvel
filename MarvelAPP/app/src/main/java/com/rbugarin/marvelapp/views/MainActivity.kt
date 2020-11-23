package com.rbugarin.marvelapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.adapters.*
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.characters.CharacterView
import com.rbugarin.marvelapp.models.comics.ComicView
import com.rbugarin.marvelapp.models.creators.CreatorView
import com.rbugarin.marvelapp.models.events.EventView
import com.rbugarin.marvelapp.models.series.SerieView
import com.rbugarin.marvelapp.models.stories.StorieView
import com.rbugarin.marvelapp.viewmodels.MainActivityViewModel

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}
class MainActivity : AppCompatActivity() {

    val mainActivityViewModel : MainActivityViewModel by viewModels()

    private lateinit var rbCreator : RadioButton
    private lateinit var rbComics : RadioButton
    private lateinit var rbEvents : RadioButton
    private lateinit var rbSeries : RadioButton
    private lateinit var rbStories : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterAdapter = CharacterAdapter()

        val bAllCharacters = findViewById<Button>(R.id.buttonAllCharacters)
        bAllCharacters.setOnClickListener {
            val intent = Intent(this, AllCharacters::class.java)
            startActivity(intent)
        }
        rbCreator = findViewById(R.id.rbCreators)
        rbComics = findViewById(R.id.rbComics)
        rbEvents = findViewById(R.id.rbEvents)
        rbSeries = findViewById(R.id.rbSeries)
        rbStories = findViewById(R.id.rbStories)

        rbCreator.setOnCheckedChangeListener(changeRadio)
        rbComics.setOnCheckedChangeListener(changeRadio)
        rbEvents.setOnCheckedChangeListener(changeRadio)
        rbSeries.setOnCheckedChangeListener(changeRadio)
        rbStories.setOnCheckedChangeListener(changeRadio)
    }
    private val changeRadio = CompoundButton.OnCheckedChangeListener { button, checked ->
            if(rbCreator.isChecked){
                val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerDatas)
                val creatorAdapter = CreatorAdapter()

                recyclerViewData.layoutManager = LinearLayoutManager(this)
                recyclerViewData.adapter = creatorAdapter

                mainActivityViewModel.marvelListLiveDataCreator.observe(this,
                    Observer<List<CreatorView>> {
                        creatorAdapter.addResults(it,mainActivityViewModel.hasNextCreators)
                        creatorAdapter.notifyDataSetChanged()
                    })
                creatorAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
                    override fun onBottomReached() {
                        mainActivityViewModel.getCreators()
                    }
                })
                mainActivityViewModel.getCreators()
            }
            if(rbComics.isChecked){
                val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerDatas)
                val comicAdapter = ComicsAdapter()

                recyclerViewData.layoutManager = LinearLayoutManager(this)
                recyclerViewData.adapter = comicAdapter

                mainActivityViewModel.marvelListLiveDataComic.observe(this,
                    Observer<List<ComicView>> {
                        comicAdapter.addResults(it,mainActivityViewModel.hasNextComics)
                        comicAdapter.notifyDataSetChanged()
                    })
                comicAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
                    override fun onBottomReached() {
                        mainActivityViewModel.getComics()
                    }
                })
                mainActivityViewModel.getComics()
            }
            if(rbEvents.isChecked){
                val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerDatas)
                val eventsAdapter = EventsAdapter()

                recyclerViewData.layoutManager = LinearLayoutManager(this)
                recyclerViewData.adapter = eventsAdapter

                mainActivityViewModel.marvelListLiveDataEvent.observe(this,
                    Observer<List<EventView>> {
                        eventsAdapter.addResults(it,mainActivityViewModel.hasNextEvents)
                        eventsAdapter.notifyDataSetChanged()
                    })
                eventsAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
                    override fun onBottomReached() {
                        mainActivityViewModel.getEvents()
                    }
                })
                mainActivityViewModel.getEvents()
            }
            if(rbSeries.isChecked){
                val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerDatas)
                val seriesAdapter = SeriesAdapter()

                recyclerViewData.layoutManager = LinearLayoutManager(this)
                recyclerViewData.adapter = seriesAdapter

                mainActivityViewModel.marvelListLiveDataSerie.observe(this,
                    Observer<List<SerieView>> {
                        seriesAdapter.addResults(it,mainActivityViewModel.hasNextSeries)
                        seriesAdapter.notifyDataSetChanged()
                    })
                seriesAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
                    override fun onBottomReached() {
                        mainActivityViewModel.getSeries()
                    }
                })
                mainActivityViewModel.getSeries()
            }
            if(rbStories.isChecked){
                val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerDatas)
                val storiesAdapter = StoriesAdapter()

                recyclerViewData.layoutManager = LinearLayoutManager(this)
                recyclerViewData.adapter = storiesAdapter

                mainActivityViewModel.marvelListLiveDataStorie.observe(this,
                    Observer<List<StorieView>> {
                        storiesAdapter.addResults(it,mainActivityViewModel.hasNextStories)
                        storiesAdapter.notifyDataSetChanged()
                    })
                storiesAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
                    override fun onBottomReached() {
                        mainActivityViewModel.getStories()
                    }
                })
                mainActivityViewModel.getStories()
            }
    }
}