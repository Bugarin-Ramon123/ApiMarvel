package com.rbugarin.marvelapp.models.events

import com.google.gson.annotations.SerializedName


data class EventsThumbnail (

	@SerializedName("path") val path : String,
	@SerializedName("extension") val extension : String
)