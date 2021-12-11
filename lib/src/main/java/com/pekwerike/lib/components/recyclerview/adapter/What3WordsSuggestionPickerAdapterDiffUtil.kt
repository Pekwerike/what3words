package com.pekwerike.lib.components.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pekwerike.lib.domain.Suggestion

internal object What3WordsSuggestionPickerAdapterDiffUtil : DiffUtil.ItemCallback<Suggestion>() {
    override fun areItemsTheSame(oldItem: Suggestion, newItem: Suggestion): Boolean {
        return oldItem.words == newItem.words
    }

    override fun areContentsTheSame(oldItem: Suggestion, newItem: Suggestion): Boolean {
        return oldItem == newItem
    }
}