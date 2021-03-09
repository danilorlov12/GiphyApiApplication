package com.example.giphyapiapplication.model

import com.google.gson.annotations.SerializedName

class GifList (
    @SerializedName("data")
    var gifs : List<Gif>
)

class Gif(
    val url: String,
    val images : Images
)

class Images(
    val fixed_height_small : FixedHeightSmall
)

class FixedHeightSmall(
    val url : String
)
