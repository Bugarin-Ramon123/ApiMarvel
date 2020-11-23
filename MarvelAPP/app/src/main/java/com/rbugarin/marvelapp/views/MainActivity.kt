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
import com.rbugarin.marvelapp.adapters.CharacterAdapter
import com.rbugarin.marvelapp.adapters.CreatorAdapter
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.characters.CharacterView
import com.rbugarin.marvelapp.models.creators.CreatorView
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

            }
            if(rbEvents.isChecked){

            }
            if(rbSeries.isChecked){

            }
            if(rbStories.isChecked){

            }
    }
}