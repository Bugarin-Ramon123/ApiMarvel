package com.rbugarin.marvelapp.models.events

import com.google.gson.annotations.SerializedName


data class EventsResults (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("description") val description : String,
	@SerializedName("thumbnail") val thumbnail : EventsThumbnail

	)