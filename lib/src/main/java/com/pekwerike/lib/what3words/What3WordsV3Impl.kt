package com.pekwerike.lib.what3words

import android.text.Editable
import android.text.TextWatcher
import com.pekwerike.lib.InterfaceAdapters.toDomain
import com.pekwerike.lib.InterfaceAdapters.toServerRequest
import com.pekwerike.lib.isValidW3W
import com.pekwerike.lib.domain.CustomAutoSuggestRequest
import com.what3words.androidwrapper.What3WordsV3
import com.what3words.javawrapper.request.AutosuggestRequest
import com.what3words.javawrapper.response.Autosuggest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

internal class What3WordsV3Impl(
    private val w3wAndroid: What3WordsV3
) : TextWatcher, What3WordsV3Interface {

    private val _suggestions = MutableSharedFlow<Autosuggest?>(0)
    val suggestions: SharedFlow<Autosuggest?> = _suggestions

    private var customAutoSuggestRequest: CustomAutoSuggestRequest? = null

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s.isNullOrBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                _suggestions.emit(null)
            }
            return
        }

        if (s.isValidW3W()) {
            CoroutineScope(Dispatchers.IO).launch {
                val result: Autosuggest =
                    w3wAndroid.autosuggest(s.toString().replace("/", "")).apply {
                        configureCustomRequest()
                    }.execute()
                withContext(Dispatchers.Main) {
                    _suggestions.emit(result)
                }
            }

        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override suspend fun convertToCoordinates(suggestion: com.pekwerike.lib.domain.Suggestion): com.pekwerike.lib.domain.Coordinates {
        return withContext(Dispatchers.IO) {
            w3wAndroid.convertToCoordinates(suggestion.words)
                .execute().coordinates.toDomain()
        }
    }

    override fun setCustomAutoSuggestionRequest(customAutoSuggestRequest: CustomAutoSuggestRequest) {
        this.customAutoSuggestRequest = customAutoSuggestRequest
    }

    private fun AutosuggestRequest.Builder.configureCustomRequest() {
        customAutoSuggestRequest?.let { suggestRequest: CustomAutoSuggestRequest ->
            if (suggestRequest.focus != null) {
                focus(suggestRequest.focus!!.toServerRequest())
            }
            if (suggestRequest.clipToBoundingBox != null) {
                clipToBoundingBox(suggestRequest.clipToBoundingBox!!.toServerRequest())
            }
            if (suggestRequest.clipToCircle != null) {
                clipToCircle(
                    suggestRequest.clipToCircle!!.first.toServerRequest(),
                    suggestRequest.clipToCircle!!.second
                )
            }
            if (suggestRequest.clipToCountry.isNotBlank()) {
                clipToCountry(suggestRequest.clipToCountry)
            }
            if (suggestRequest.clipToPolygon != null) {
                clipToPolygon(suggestRequest.clipToPolygon!!.toServerRequest())
            }
            nResults(suggestRequest.numberOfResults)
            nFocusResults(suggestRequest.numberOfFocusResults)
        }
    }

}