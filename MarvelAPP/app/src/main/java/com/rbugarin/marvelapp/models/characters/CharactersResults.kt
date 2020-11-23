package com.rbugarin.marvelapp.models.characters

import com.google.gson.annotations.SerializedName

data class CharactersResults (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String,
	@SerializedName("modified") val modified : String,
	@SerializedName("thumbnail") val thumbnail : CharactersThumbnail,
	@SerializedName("resourceURI") val resourceURI : String

)