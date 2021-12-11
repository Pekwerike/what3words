package com.pekwerike.what3words

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pekwerike.lib.domain.CustomAutoSuggestRequest
import com.pekwerike.lib.components.text.What3WordsEditText

const val api_key = "2JUTPOT1"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       findViewById<What3WordsEditText>(
            R.id.what_3_words_edit_text
        ).apply {
            setApiKey(api_key)
            setOnWhat3WordsAddressSelectedListener {
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }
            setCustomAutoSuggestRequest(
                CustomAutoSuggestRequest.Builder().apply {
                    numberOfResults(4)
                }.build()
            )
        }
    }
}


