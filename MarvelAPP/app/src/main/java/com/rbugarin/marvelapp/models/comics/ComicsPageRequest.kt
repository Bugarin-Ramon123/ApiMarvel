package com.rbugarin.marvelapp.models.comics

import com.google.gson.annotations.SerializedName

class ComicsPageRequest (
    @SerializedName("results") val results : List<ComicsResults>
)

