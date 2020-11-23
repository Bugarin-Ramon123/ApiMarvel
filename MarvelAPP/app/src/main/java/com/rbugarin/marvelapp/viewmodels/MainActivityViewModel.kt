package com.rbugarin.marvelapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rbugarin.marvelapp.models.characters.CharacterView
import com.rbugarin.marvelapp.models.comics.ComicView
import com.rbugarin.marvelapp.models.creators.CreatorView
import com.rbugarin.marvelapp.models.events.EventView
import com.rbugarin.marvelapp.models.series.SerieView
import com.rbugarin.marvelapp.models.stories.StorieView
import com.rbugarin.marvelapp.repositories.MarvelRepository
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application) : AndroidViewModel(application){
    private val marvelRepository = MarvelRepository()

    private var isLoadingCharacters = false
    var hasNextCharacters = true
    var hasNextCreators = true


    val marvelListLiveDataCharacter = MutableLiveData<List<CharacterView>>()
    val marvelListLiveDataComic = MutableLiveData<List<ComicView>>()
    val marvelListLiveDataCreator = MutableLiveData<List<CreatorView>>()
    val marvelListLiveDataEvent = MutableLiveData<List<EventView>>()
    val marvelListLiveDataSerie = MutableLiveData<List<SerieView>>()
    val marvelListLiveDataStorie = MutableLiveData<List<StorieView>>()

    fun getCharacters() {
            viewModelScope.launch {
                val characterPageRequest = marvelRepository.getPageCharacter()
                val listCharactersView = characterPageRequest.results.map { result ->
                    CharacterView(
                        result.thumbnail.path,
                        result.name,
                        result.description
                    )
                }
                marvelListLiveDataCharacter.postValue(listCharactersView)
            }
    }

    fun getComics() {
        viewModelScope.launch {
            val comicPageRequest = marvelRepository.getPageComics()
            val listComicsView = comicPageRequest.results.map { result ->
                ComicView(
                    result.images[0],
                    result.title,
                    result.description
                )
            }
            marvelListLiveDataComic.postValue(listComicsView)
        }
    }

    fun getCreators() {
        viewModelScope.launch {
            val creatorPageRequest = marvelRepository.getPageCreators()
            val listCreatorsView = creatorPageRequest.results.map { result ->
                CreatorView(
                    result.thumbnail.path,
                    result.firstName,
                    result.lastName
                )
            }
            marvelListLiveDataCreator.postValue(listCreatorsView)
        }
    }

    fun getEvents() {
        viewModelScope.launch {
            val eventPageRequest = marvelRepository.getPageEvents()
            val listEventsView = eventPageRequest.results.map { result ->
                EventView(
                    result.thumbnail.path,
                    result.title,
                    result.description
                )
            }
            marvelListLiveDataEvent.postValue(listEventsView)
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            val seriesPageRequest = marvelRepository.getPageSeries()
            val listSeriesView = seriesPageRequest.results.map { result ->
                SerieView(
                    result.thumbnail.path,
                    result.title,
                    result.description
                )
            }
            marvelListLiveDataSerie.postValue(listSeriesView)
        }
    }

    fun getStories() {
        viewModelScope.launch {
            val storiesPageRequest = marvelRepository.getPageStories()
            val listStoriesView = storiesPageRequest.results.map { result ->
                StorieView(
                    result.thumbnail,
                    result.title,
                    result.description
                )
            }
            marvelListLiveDataStorie.postValue(listStoriesView)
        }
    }

}