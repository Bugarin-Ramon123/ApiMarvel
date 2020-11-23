package com.rbugarin.marvelapp.models.series

import com.google.gson.annotations.SerializedName


data class SeriesResults (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("description") val description : String,
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("type") val type : String,
	@SerializedName("modified") val modified : String,
	@SerializedName("thumbnail") val thumbnail : SeriesThumbnail,
	@SerializedName("next") val next : String,
	@SerializedName("previous") val previous : String
)