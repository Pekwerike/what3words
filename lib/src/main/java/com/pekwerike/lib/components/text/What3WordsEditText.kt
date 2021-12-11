package com.pekwerike.lib.components.text

import android.content.Context
import android.util.AttributeSet
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.pekwerike.lib.InterfaceAdapters.toDomainList
import com.pekwerike.lib.R
import com.pekwerike.lib.what3words.What3WordsV3Impl
import com.pekwerike.lib.domain.CustomAutoSuggestRequest
import com.pekwerike.lib.domain.Suggestion
import com.pekwerike.lib.components.recyclerview.What3WordsSuggestionPicker
import com.pekwerike.lib.components.text.What3WordsEditTextExtension.attachAddressPicker
import com.what3words.androidwrapper.What3WordsV3
import com.what3words.javawrapper.response.Autosuggest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class What3WordsEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?
) : AppCompatEditText(context, attrs) {

    private var onWhat3WordsAddressSelectedListener: OnWhat3WordsAddressSelectedListener? = null
    private var what3WordsV3Impl: What3WordsV3Impl? = null
    private var customAutoSuggestRequest: CustomAutoSuggestRequest? = null

    internal val what3wordsSuggestionPicker = What3WordsSuggestionPicker(context, attrs)
    private val density = resources.displayMetrics.density

    init {
        background = ContextCompat.getDrawable(context, R.drawable.white_gray_border)
        hint = resources.getString(R.string.w3w_edit_text_default_hint)
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.search_icon, 0)
        setPadding(
            (8 * density).roundToInt(),
            paddingTop,
            (8 * density).roundToInt(),
            paddingBottom
        )
        // attach the auto suggestion picker to the edit text immediately after it becomes visible
        attachAddressPicker()
        what3wordsSuggestionPicker.setOnSuggestionClickListener { suggestion: Suggestion ->
            CoroutineScope(Dispatchers.Main).launch {
                val coordinate = what3WordsV3Impl?.convertToCoordinates(suggestion)
                onWhat3WordsAddressSelectedListener?.onAddressSelected(coordinate!!)
            }
        }
    }

    /**
     * When an action is done on a proposed what3words address,
     * this listener is called. This function will convert the selected what3word address
     * to a value that represents the what3words address's gps coordinates.
     * @param onWhat3WordsAddressSelectedListener implementation
     */
    fun setOnWhat3WordsAddressSelectedListener(onWhat3WordsAddressSelectedListener: OnWhat3WordsAddressSelectedListener) {
        this.onWhat3WordsAddressSelectedListener = onWhat3WordsAddressSelectedListener
    }

    /** Set your What3Words API Key which will be used to get suggestions and coordinates (if enabled)
     *
     * @param key your API key from what3words developer dashboard
     * @return same [What3WordsEditText] instance
     */
    fun setApiKey(key: String): What3WordsEditText {
        createWhat3WordAndroidWrapper(key)
        return this
    }

    /** Creates instance of What3Words android wrapper V.3
     *
     * @param key your API key from what3words developer dashboard
     * @return void
     */
    private fun createWhat3WordAndroidWrapper(key: String) {
        val androidWrapper = What3WordsV3(
            key,
            context
        )
        what3WordsV3Impl = What3WordsV3Impl(androidWrapper)
        if (customAutoSuggestRequest != null) {
            what3WordsV3Impl?.setCustomAutoSuggestionRequest(customAutoSuggestRequest!!)
        }
        addTextChangedListener(what3WordsV3Impl)
        collectSuggestions()
    }

    /** Enables What3WordsEditText to start observing address suggestions as user types
     *
     */
    private fun collectSuggestions() {
        CoroutineScope(Dispatchers.Main).launch {
            what3WordsV3Impl?.suggestions?.collect { autoSuggest: Autosuggest? ->
                if (autoSuggest == null) {
                    what3wordsSuggestionPicker.updateSuggestions(listOf())
                    return@collect
                }
                if (autoSuggest.isSuccessful) {
                    val suggestions = autoSuggest.suggestions.toDomainList()
                    what3wordsSuggestionPicker.updateSuggestions(suggestions)
                } else {
                    // TODO Handle error notification gracefully
                }
            }
        }
    }

    /** Sets further configuration on What3Words AutoSuggestionResponse. Check [CustomAutoSuggestRequest.Builder]
     * for proper documentation on how to go about building a CustomAutoSuggestRequest
     *
     * **/
    fun setCustomAutoSuggestRequest(customAutoSuggestRequest: CustomAutoSuggestRequest) {
        this.customAutoSuggestRequest = customAutoSuggestRequest
        what3WordsV3Impl?.setCustomAutoSuggestionRequest(customAutoSuggestRequest)
    }

}