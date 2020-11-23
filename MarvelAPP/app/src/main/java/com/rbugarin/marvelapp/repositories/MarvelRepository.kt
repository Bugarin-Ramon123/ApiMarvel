package com.rbugarin.marvelapp.repositories

import com.rbugarin.marvelapp.models.characters.CharacterPageRequest
import com.rbugarin.marvelapp.api.API
import com.rbugarin.marvelapp.models.comics.ComicsPageRequest
import com.rbugarin.marvelapp.models.creators.CreatorsPageRequest
import com.rbugarin.marvelapp.models.events.EventsPageRequest
import com.rbugarin.marvelapp.models.series.SeriesPageRequest
import com.rbugarin.marvelapp.models.stories.StoriesPageRequest
import com.rbugarin.marvelapp.net.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MarvelRepository {
    private val apidatos = API()
    suspend fun getPageCharacter(): CharacterPageRequest {
        return suspendCoroutine{ continuation->
            RetrofitInstance.marvelService.getCharactersPage(apidatos.publicKey,apidatos.timeStamp,apidatos.getHash()).enqueue(object: Callback<CharacterPageRequest>{
                override fun onResponse(
                    call: Call<CharacterPageRequest>,
                    response: Response<CharacterPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CharacterPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getPageComics(): ComicsPageRequest {
        return suspendCoroutine{ continuation->
            RetrofitInstance.marvelService.getComics(apidatos.publicKey,apidatos.timeStamp,apidatos.getHash()).enqueue(object: Callback<ComicsPageRequest>{
                override fun onResponse(
                    call: Call<ComicsPageRequest>,
                    response: Response<ComicsPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<ComicsPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getPageCreators(): CreatorsPageRequest {
        return suspendCoroutine{ continuation->
            RetrofitInstance.marvelService.getCreators(apidatos.publicKey,apidatos.timeStamp,apidatos.getHash()).enqueue(object: Callback<CreatorsPageRequest>{
                override fun onResponse(
                    call: Call<CreatorsPageRequest>,
                    response: Response<CreatorsPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<CreatorsPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getPageEvents(): EventsPageRequest {
        return suspendCoroutine{ continuation->
            RetrofitInstance.marvelService.getEvents(apidatos.publicKey,apidatos.timeStamp,apidatos.getHash()).enqueue(object: Callback<EventsPageRequest>{
                override fun onResponse(
                    call: Call<EventsPageRequest>,
                    response: Response<EventsPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<EventsPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getPageSeries(): SeriesPageRequest {
        return suspendCoroutine{ continuation->
            RetrofitInstance.marvelService.getSeries(apidatos.publicKey,apidatos.timeStamp,apidatos.getHash()).enqueue(object: Callback<SeriesPageRequest>{
                override fun onResponse(
                    call: Call<SeriesPageRequest>,
                    response: Response<SeriesPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<SeriesPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun getPageStories(): StoriesPageRequest {
        return suspendCoroutine{ continuation->
            RetrofitInstance.marvelService.getStories(apidatos.publicKey,apidatos.timeStamp,apidatos.getHash()).enqueue(object: Callback<StoriesPageRequest>{
                override fun onResponse(
                    call: Call<StoriesPageRequest>,
                    response: Response<StoriesPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                }
                override fun onFailure(call: Call<StoriesPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}