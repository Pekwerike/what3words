package com.pekwerike.lib

import org.junit.Test

import org.junit.Assert.*

/**
 * local unit test, for the functions in the Utils file
 */
class UtilsTest {

    @Test
    fun `is valid word3words`() {
        val wordOne = "filled.count.soap"
        val wordTwo = "///rain.game.make"
        assertEquals(true, wordOne.isValidW3W())
        assertEquals(true, wordTwo.isValidW3W())
    }

    @Test
    fun `is not valid word3words`() {
        val wordOne = "filled."
        val wordTwo = "___.filled.rain.game"
        val wordThree = "make.me//arat"
        val wordFour = "///heavens.gate."
        assertEquals(false, wordOne.isValidW3W())
        assertEquals(false, wordTwo.isValidW3W())
        assertEquals(false, wordThree.isValidW3W())
        assertEquals(false, wordFour.isValidW3W())
    }
}