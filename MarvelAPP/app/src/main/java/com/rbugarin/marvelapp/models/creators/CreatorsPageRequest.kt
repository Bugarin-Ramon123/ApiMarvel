package com.rbugarin.marvelapp.models.creators

import com.google.gson.annotations.SerializedName

class CreatorsPageRequest (
    @SerializedName("results") val results : List<CreatorsResults>
)