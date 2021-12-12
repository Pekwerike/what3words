package com.pekwerike.lib.components.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pekwerike.lib.domain.Suggestion

internal object What3WordsSuggestionPickerAdapterDiffUtil : DiffUtil.ItemCallback<Suggestion>() {
    override fun areItemsTheSame(oldItem: Suggestion, newItem: Suggestion): Boolean =
        oldItem.words == newItem.words


    override fun areContentsTheSame(oldItem: Suggestion, newItem: Suggestion): Boolean =
        oldItem == newItem

}