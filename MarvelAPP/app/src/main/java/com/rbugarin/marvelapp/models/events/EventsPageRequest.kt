package com.rbugarin.marvelapp.models.events

import com.google.gson.annotations.SerializedName

class EventsPageRequest (
    @SerializedName("results") val results : List<EventsResults>
)