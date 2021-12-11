package com.pekwerike.lib.components.text

import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat

internal object What3WordsEditTextExtension {

    internal fun What3WordsEditText.attachAddressPicker() {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val editTextMarginParam: ViewGroup.MarginLayoutParams =
                    layoutParams as ViewGroup.MarginLayoutParams

                val recyclerViewParams = ViewGroup.MarginLayoutParams(
                    width,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                what3wordsSuggestionPicker.x = editTextMarginParam.leftMargin.toFloat()
                what3wordsSuggestionPicker.y = y + height - 3

                val group = parent as? ViewGroup
                group?.let { viewGroup ->
                    if (viewGroup is LinearLayout) {
                        viewGroup.orientation = LinearLayout.VERTICAL
                    } else if (viewGroup is LinearLayoutCompat) {
                        viewGroup.orientation = LinearLayoutCompat.VERTICAL
                    }
                    viewGroup.addView(what3wordsSuggestionPicker, recyclerViewParams)
                }
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}