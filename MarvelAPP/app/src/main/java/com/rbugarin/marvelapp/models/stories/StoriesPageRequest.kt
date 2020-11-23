package com.rbugarin.marvelapp.models.stories

import com.google.gson.annotations.SerializedName

class StoriesPageRequest (
    @SerializedName("results") val results : List<StoriesResults>
)