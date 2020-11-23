package com.rbugarin.marvelapp.net

import com.rbugarin.marvelapp.BASE_URL
import com.rbugarin.marvelapp.net.services.MarvelService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        @JvmStatic
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @JvmStatic
        public val marvelService = retrofit.create(MarvelService::class.java)
    }
}