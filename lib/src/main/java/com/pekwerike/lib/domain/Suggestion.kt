package com.pekwerike.lib.domain

data class Suggestion(
    val country: String?,
    private val nearestPlace: String?,
    val words: String?,
    private val distanceToFocusKm: Int?,
    val rank: Int?,
    val language: String?,
) {
    val distanceToFocusInKm: String
        get() = if (distanceToFocusKm != null) "${distanceToFocusKm}km" else ""
    val formattedNearestPlace: String
        get() = if (nearestPlace != null) "near $nearestPlace" else ""
}