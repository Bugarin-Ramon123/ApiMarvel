package com.rbugarin.marvelapp.models.creators

import com.google.gson.annotations.SerializedName


data class CreatorsResults (

	@SerializedName("id") val id : Int,
	@SerializedName("firstName") val firstName : String,
	@SerializedName("middleName") val middleName : String,
	@SerializedName("lastName") val lastName : String,
	@SerializedName("suffix") val suffix : String,
	@SerializedName("fullName") val fullName : String,
	@SerializedName("modified") val modified : String,
	@SerializedName("thumbnail") val thumbnail : CreatorsThumbnail,
	@SerializedName("resourceURI") val resourceURI : String
)