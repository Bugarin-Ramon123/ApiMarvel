package com.rbugarin.marvelapp.models.creators

import com.google.gson.annotations.SerializedName

data class CreatorsThumbnail (

	@SerializedName("path") val path : String,
	@SerializedName("extension") val extension : String
)