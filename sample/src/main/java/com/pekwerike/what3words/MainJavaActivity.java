package com.pekwerike.what3words;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pekwerike.lib.domain.Coordinates;
import com.pekwerike.lib.domain.CustomAutoSuggestRequest;
import com.pekwerike.lib.components.text.What3WordsEditText;

public class MainJavaActivity extends AppCompatActivity {
    private static final String mApiKey = "what3wordsApiKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        What3WordsEditText what3WordsEditText = findViewById(R.id.what3words_edit_text_two);

        what3WordsEditText.setApiKey(mApiKey)
                .setCustomAutoSuggestRequest(
                        new CustomAutoSuggestRequest.Builder()
                                .focus(new Coordinates(42.123, 235.263))
                                .numberOfResults(10)
                                .clipToCountry("GB, BE")
                                .build()
                );
    }
}