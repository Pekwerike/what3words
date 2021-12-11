package com.pekwerike.lib.what3words

import com.pekwerike.lib.domain.Coordinates
import com.pekwerike.lib.domain.CustomAutoSuggestRequest
import com.pekwerike.lib.domain.Suggestion

internal interface What3WordsV3Interface {
    suspend fun convertToCoordinates(suggestion: Suggestion): Coordinates
    fun setCustomAutoSuggestionRequest(customAutoSuggestRequest: CustomAutoSuggestRequest)
}