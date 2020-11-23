package com.rbugarin.marvelapp.models.series

import com.google.gson.annotations.SerializedName

class SeriesPageRequest  (
    @SerializedName("results") val results : List<SeriesResults>
)