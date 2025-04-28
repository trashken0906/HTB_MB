package com.ltu.m7019e.themoviedb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("results")
    val results: List<Review>
)
