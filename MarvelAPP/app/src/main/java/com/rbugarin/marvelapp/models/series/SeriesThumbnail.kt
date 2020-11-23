package com.rbugarin.marvelapp.models.series

import com.google.gson.annotations.SerializedName

data class SeriesThumbnail (

	@SerializedName("path") val path : String,
	@SerializedName("extension") val extension : String
)