package com.pekwerike.lib.domain


data class BoundingBox(
    var sw: Coordinates? = null,
    var ne: Coordinates? = null
)