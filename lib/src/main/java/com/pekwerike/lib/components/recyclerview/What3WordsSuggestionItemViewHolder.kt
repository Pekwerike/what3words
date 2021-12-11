package com.pekwerike.lib.components.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.pekwerike.lib.R
import com.pekwerike.lib.domain.Suggestion

internal class What3WordsSuggestionItemViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    fun bindData(
        suggestion: Suggestion,
        onSuggestionClicked: (Suggestion) -> Unit
    ) {
        val wordsTextView = view.findViewById<TextView>(R.id.words_text_view)
        val nearestPlaceTextView = view.findViewById<TextView>(R.id.nearest_place_text_view)
        val group = view.findViewById<ConstraintLayout>(R.id.group)
        val focusKm = view.findViewById<TextView>(R.id.distance_to_focus_text_view)

        wordsTextView.text = suggestion.words
        nearestPlaceTextView.text = suggestion.formattedNearestPlace
        focusKm.text = suggestion.distanceToFocusInKm
        group.setOnClickListener {
            onSuggestionClicked(suggestion)
        }
    }

    companion object {
        fun createViewHolder(parent: ViewGroup): What3WordsSuggestionItemViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(
                R.layout.suggested_address_item,
                parent,
                false
            )
            return What3WordsSuggestionItemViewHolder(layout)
        }
    }
}