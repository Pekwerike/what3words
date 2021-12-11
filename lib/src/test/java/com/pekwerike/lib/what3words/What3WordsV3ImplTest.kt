package com.pekwerike.lib.what3words

import app.cash.turbine.test
import com.pekwerike.lib.domain.Coordinates
import com.pekwerike.lib.domain.Suggestion
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class What3WordsV3ImplTest {

    @Test
    fun `convert Suggestion to Coordinate`() = runBlocking {
        val suggestion = Suggestion(
            country = "UK",
            nearestPlace = "near Peckham, London",
            words = "living.near.heaven",
            distanceToFocusKm = 13,
            rank = 3,
            language = "EN-UK"
        )
        val what3WordsV3Impl = mock(What3WordsV3Impl::class.java)
        `when`(what3WordsV3Impl.convertToCoordinates(suggestion))
            .thenReturn(Coordinates(longitude = 103.0, latitude = 13.0))

        val coordinate = what3WordsV3Impl.convertToCoordinates(suggestion)
        assertEquals(103.0, coordinate.longitude, 0.0)
        assertEquals(13.0, coordinate.latitude, 0.0)
    }

}