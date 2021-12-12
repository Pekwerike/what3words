package com.pekwerike.lib.components.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pekwerike.lib.R
import com.pekwerike.lib.domain.Suggestion
import com.pekwerike.lib.components.recyclerview.adapter.What3WordsSuggestionPickerAdapter

internal class What3WordsSuggestionPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var onSuggestionClicked: ((Suggestion) -> Unit)? = null
    private val what3WordsSuggestionPickerAdapter =
        What3WordsSuggestionPickerAdapter { suggestion: Suggestion ->
            onSuggestionClicked?.invoke(suggestion)
        }

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = what3WordsSuggestionPickerAdapter
        background = ContextCompat.getDrawable(context, R.drawable.white_gray_border)
    }

    fun updateSuggestions(suggestions: List<Suggestion>) {
        what3WordsSuggestionPickerAdapter.submitList(suggestions)
    }

    internal fun setOnSuggestionClickListener(
        onSuggestionClicked: (Suggestion) -> Unit
    ) {
        this.onSuggestionClicked = onSuggestionClicked
    }
}