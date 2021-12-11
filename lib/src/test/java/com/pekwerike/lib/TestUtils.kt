package com.pekwerike.lib.what3words

import com.what3words.javawrapper.response.Suggestion
import org.mockito.Mockito

fun getSuggestionList(): List<Suggestion> {
    val suggestionResponseOne = Mockito.mock(Suggestion::class.java)
    Mockito.`when`(suggestionResponseOne.country).thenReturn("BE")
    Mockito.`when`(suggestionResponseOne.distanceToFocusKm).thenReturn(18)
    Mockito.`when`(suggestionResponseOne.nearestPlace).thenReturn("London, UK")
    Mockito.`when`(suggestionResponseOne.words).thenReturn("filled.count.soap")
    Mockito.`when`(suggestionResponseOne.rank).thenReturn(3)
    Mockito.`when`(suggestionResponseOne.language).thenReturn("En-UK")

    val suggestionResponseTwo = Mockito.mock(Suggestion::class.java)
    Mockito.`when`(suggestionResponseTwo.country).thenReturn("GB")
    Mockito.`when`(suggestionResponseTwo.distanceToFocusKm).thenReturn(180)
    Mockito.`when`(suggestionResponseTwo.nearestPlace).thenReturn("Landis, Belgium")
    Mockito.`when`(suggestionResponseTwo.words).thenReturn("filled.counts.aslo")
    Mockito.`when`(suggestionResponseTwo.rank).thenReturn(3)
    Mockito.`when`(suggestionResponseTwo.language).thenReturn("En-GB")

    val suggestionResponseThree =
        Mockito.mock(Suggestion::class.java)
    Mockito.`when`(suggestionResponseThree.country).thenReturn("NG")
    Mockito.`when`(suggestionResponseThree.distanceToFocusKm).thenReturn(13)
    Mockito.`when`(suggestionResponseThree.nearestPlace).thenReturn("Lagos, Nigeria")
    Mockito.`when`(suggestionResponseThree.words).thenReturn("heavens.gate.open")
    Mockito.`when`(suggestionResponseThree.rank).thenReturn(2)
    Mockito.`when`(suggestionResponseThree.language).thenReturn("En-UK")
    return listOf(suggestionResponseOne, suggestionResponseTwo, suggestionResponseThree)
}