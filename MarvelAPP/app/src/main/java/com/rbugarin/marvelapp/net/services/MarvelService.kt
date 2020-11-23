package com.rbugarin.marvelapp.net.services

import com.rbugarin.marvelapp.models.characters.CharacterPageRequest
import com.rbugarin.marvelapp.models.comics.ComicsPageRequest
import com.rbugarin.marvelapp.models.creators.CreatorsPageRequest
import com.rbugarin.marvelapp.models.events.EventsPageRequest
import com.rbugarin.marvelapp.models.series.SeriesPageRequest
import com.rbugarin.marvelapp.models.stories.StoriesPageRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("character")
    fun getCharactersPage(@Query("apikey")apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String) : Call<CharacterPageRequest>

    @GET("comics")
    fun getComics(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String) : Call<ComicsPageRequest>

    @GET("creators")
    fun getCreators(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String) : Call<CreatorsPageRequest>

    @GET("events")
    fun getEvents(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String) : Call<EventsPageRequest>

    @GET("series")
    fun getSeries(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String) : Call<SeriesPageRequest>

    @GET("stories")
    fun getStories(@Query("apikey") apiKey: String, @Query("ts") ts: String, @Query("hash") hash: String) : Call<StoriesPageRequest>


}