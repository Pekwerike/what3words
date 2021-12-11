package com.pekwerike.lib.components.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pekwerike.lib.domain.Suggestion
import com.pekwerike.lib.components.recyclerview.What3WordsSuggestionItemViewHolder

internal class What3WordsSuggestionPickerAdapter(
    private val onSuggestionClicked: (Suggestion) -> Unit
) : ListAdapter<Suggestion, What3WordsSuggestionItemViewHolder>(
    What3WordsSuggestionPickerAdapterDiffUtil
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): What3WordsSuggestionItemViewHolder =
        What3WordsSuggestionItemViewHolder.createViewHolder(parent)


    override fun onBindViewHolder(holder: What3WordsSuggestionItemViewHolder, position: Int) {
        holder.bindData(currentList[position], onSuggestionClicked)
    }
}
