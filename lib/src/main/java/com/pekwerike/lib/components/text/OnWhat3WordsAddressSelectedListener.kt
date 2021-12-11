package com.pekwerike.lib.components.text

import com.pekwerike.lib.domain.Coordinates

fun interface OnWhat3WordsAddressSelectedListener {
    fun onAddressSelected(coordinates: Coordinates)
}