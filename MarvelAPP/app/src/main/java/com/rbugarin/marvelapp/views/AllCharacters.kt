package com.rbugarin.marvelapp.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rbugarin.marvelapp.R
import com.rbugarin.marvelapp.adapters.CharacterAdapter
import com.rbugarin.marvelapp.listeners.OnBottomReachedListener
import com.rbugarin.marvelapp.models.characters.CharacterView
import com.rbugarin.marvelapp.viewmodels.MainActivityViewModel

class AllCharacters : AppCompatActivity() {
    val mainActivityViewModel : MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_characters)

        val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerViewData)
        val characterAdapter = CharacterAdapter()

        recyclerViewData.layoutManager = LinearLayoutManager(this)
        recyclerViewData.adapter = characterAdapter

        mainActivityViewModel.marvelListLiveDataCharacter.observe(this,
            Observer<List<CharacterView>> {
                characterAdapter.addResults(it, mainActivityViewModel.hasNextCharacters)
                characterAdapter.notifyDataSetChanged()
            })

        characterAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
            override fun onBottomReached() {
                mainActivityViewModel.getCharacters()
            }
        })
        mainActivityViewModel.getCharacters()
    }

}