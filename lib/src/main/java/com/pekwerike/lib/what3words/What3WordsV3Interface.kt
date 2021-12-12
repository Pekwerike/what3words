package com.pekwerike.lib.what3words

import com.pekwerike.lib.domain.Coordinates
import com.pekwerike.lib.domain.CustomAutoSuggestRequest
import com.pekwerike.lib.domain.Suggestion
import com.what3words.javawrapper.response.Autosuggest
import kotlinx.coroutines.flow.SharedFlow

internal interface What3WordsV3Interface {
    val suggestions: SharedFlow<Autosuggest?>
    val convertedSuggestion: SharedFlow<Coordinates>
    fun convertToCoordinates(suggestion: Suggestion)
    fun setCustomAutoSuggestionRequest(customAutoSuggestRequest: CustomAutoSuggestRequest)
}