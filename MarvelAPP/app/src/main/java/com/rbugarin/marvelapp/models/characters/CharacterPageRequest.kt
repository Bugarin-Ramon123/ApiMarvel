package com.rbugarin.marvelapp.models.characters

import com.google.gson.annotations.SerializedName

data class CharacterPageRequest (

	@SerializedName("results") val results : List<CharactersResults>
)