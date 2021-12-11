package com.pekwerike.lib

import com.pekwerike.lib.domain.BoundingBox
import com.what3words.javawrapper.response.Coordinates
import com.what3words.javawrapper.response.Suggestion

internal object InterfaceAdapters {

    fun List<Suggestion>.toDomainList(): List<com.pekwerike.lib.domain.Suggestion> =
        map {
            it.toDomain()
        }


    fun Suggestion.toDomain(): com.pekwerike.lib.domain.Suggestion =
        com.pekwerike.lib.domain.Suggestion(
            country = this.country,
            nearestPlace = this.nearestPlace,
            words = this.words,
            distanceToFocusKm = this.distanceToFocusKm,
            rank = this.rank,
            language = this.language
        )

    fun Coordinates.toDomain(): com.pekwerike.lib.domain.Coordinates =
        com.pekwerike.lib.domain.Coordinates(
            longitude = lng,
            latitude = lat
        )

    fun com.pekwerike.lib.domain.Coordinates.toServerRequest(): com.what3words.javawrapper.request.Coordinates =
        com.what3words.javawrapper.request.Coordinates(
            latitude,
            longitude
        )

    fun BoundingBox.toServerRequest(): com.what3words.javawrapper.request.BoundingBox =
        com.what3words.javawrapper.request.BoundingBox(
            sw?.toServerRequest(), ne?.toServerRequest()
        )
}