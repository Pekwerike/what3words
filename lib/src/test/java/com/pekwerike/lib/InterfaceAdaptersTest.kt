package com.pekwerike.lib

import com.pekwerike.lib.InterfaceAdapters.toDomain
import com.pekwerike.lib.InterfaceAdapters.toServerRequest
import com.pekwerike.lib.domain.BoundingBox
import com.pekwerike.lib.domain.Coordinates
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.junit.Assert.*

/** Local unit test for the extension functions in the InterfaceAdapter object
 * **/

class InterfaceAdaptersTest {

    @Test
    fun `convert com_what3words_javawrapper_response_Suggestion to domain Suggestion`() {
        val mockedSuggestion = mock(com.what3words.javawrapper.response.Suggestion::class.java)
        `when`(mockedSuggestion.country).thenReturn("BE")
        `when`(mockedSuggestion.distanceToFocusKm).thenReturn(18)
        `when`(mockedSuggestion.nearestPlace).thenReturn("London, UK")
        `when`(mockedSuggestion.words).thenReturn("filled.count.soap")
        `when`(mockedSuggestion.rank).thenReturn(3)
        `when`(mockedSuggestion.language).thenReturn("En-UK")

        val domainSuggestion: com.pekwerike.lib.domain.Suggestion =
            mockedSuggestion.toDomain()

        assertEquals("BE", domainSuggestion.country)
        assertEquals("near London, UK", domainSuggestion.formattedNearestPlace)
        assertEquals("18km", domainSuggestion.distanceToFocusInKm)
        assertEquals("filled.count.soap", domainSuggestion.words)
        assertEquals("En-UK", domainSuggestion.language)
    }

    @Test
    fun `convert com_what3words_javawrapper_response_Coordinates to domain Coordinate`() {
        val mockedCoordinate = mock(com.what3words.javawrapper.response.Coordinates::class.java)
        `when`(mockedCoordinate.lat).thenReturn(119.4179)
        `when`(mockedCoordinate.lng).thenReturn(36.7783)

        val domainCoordinate: com.pekwerike.lib.domain.Coordinates =
            mockedCoordinate.toDomain()

        assertEquals(119.0, domainCoordinate.latitude, 0.5)
        assertNotEquals(119.0, domainCoordinate.latitude, 0.0)
        assertEquals(119.4179, domainCoordinate.latitude, 0.0)
        assertEquals(36.7783, domainCoordinate.longitude, 0.0)
    }

    @Test
    fun `convert domain BoundingBox to server request BoundingBox`() {
        val domainBoundingBox = BoundingBox(
            sw = Coordinates(longitude = 119.412, latitude = 36.248),
            ne = Coordinates(longitude = 222.442, latitude = 45.909)
        )
        val serverBoundingBox = domainBoundingBox.toServerRequest()
        assertEquals(domainBoundingBox.sw!!.latitude, serverBoundingBox.sw.lat, 0.0)
        assertEquals(domainBoundingBox.sw!!.longitude, serverBoundingBox.sw.lng, 0.0)
        assertEquals(domainBoundingBox.ne!!.latitude, serverBoundingBox.ne.lat, 0.0)
        assertEquals(domainBoundingBox.ne!!.longitude, serverBoundingBox.ne.lng, 0.0)

    }

    @Test
    fun `convert domain Coordinates to server request Coordinates`() {
        val domainCoordinates = Coordinates(longitude = 222.442, latitude = 45.909)
        val serverRequest = domainCoordinates.toServerRequest()
        assertEquals(domainCoordinates.longitude, serverRequest.lng, 0.0)
        assertEquals(domainCoordinates.latitude, serverRequest.lat, 0.0)
    }
}