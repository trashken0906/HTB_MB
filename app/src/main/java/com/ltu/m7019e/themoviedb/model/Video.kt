package com.ltu.m7019e.themoviedb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("key")
    val key: String,

    @SerialName("name")
    val name: String,

    @SerialName("site")
    val site: String
)
