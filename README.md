# what3words UI 

what3words UI is a lightweight library that comes with a suite of customizable android UI components that allows you to integrate the full functionalities of the what3words API into your android application without hassle. To get started you need to download this library and get your what3words developer API key by following this [link](https://what3words.com/select-plan).

Demo
<video src='https://user-images.githubusercontent.com/43956851/145692533-1d4235cb-58fe-4e07-a8ca-4e5b2ddd1a49.mp4' width = "186"/>

### Sample app screenshot
<img src="https://github.com/Pekwerike/what3words/blob/master/screenshotone.png" width="216" height="468"> <img src="https://github.com/Pekwerike/what3words/blob/master/screenshottwo.png" width="216" height="468">  


## Documentation

See the what3words public API [documentation](https://docs.what3words.com/api/v3/)

## Using in your project

AndroidManifest.xml

```xml

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.yourpackage.yourapp">

    <uses-permission android:name="android.permission.INTERNET" />
    ...
</manifest>
```

add this to build.gradle (app level)

```
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
```

Layout

```xml

<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.pekwerike.lib.ui.text.What3WordsEditText 
        android:id="@+id/what3wordsEditText"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_width="match_parent" 
        android:layout_height="wrap_content"
        android:layout_margin="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

or

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto" 
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.pekwerike.lib.ui.text.What3WordsEditText 
        android:id="@+id/what3wordsEditText"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_width="match_parent" 
        android:layout_height="wrap_content"
        android:layout_margin="16dp" />

</LinearLayout>
```

Sample usage - Kotlin

```Kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val what3wordsEditText = findViewById<What3WordsEditText>(R.id.what3wordsEditText)
        what3wordsEditText.setApiKey(api_key)
        what3wordsEditText.setOnWhat3WordsAddressSelectedListener { coordinates: Coordinates ->
            Toast.makeText(this@MainActivity, coorinates.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
```


Simple usage - Java
```java
public class MainJavaActivity extends AppCompatActivity {
    private static final String mApiKey = "what3wordsApiKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        What3WordsEditText what3WordsEditText = findViewById(R.id.what3words_edit_text_two);

        what3WordsEditText.setApiKey(mApiKey);
    }
}
```

Customizing address suggestions - Kotlin

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        //..... 
        
        what3wordsEditText.setCustomAutoSuggestRequest(
            CustomAutoSuggestRequest.Builder().apply {
                focus(Coordinates(50.153, -12.353))
                numberOfResults(5)
                numberOfFocusResults(3)
                clipToCircle(Pair(Coordinates(-40.13, 15.1), 50.0))
            }.build()
        )

        //....
        
    }
}
```

Customizing address suggestions - Java 

```java
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
```

### More auto suggestion request customization
Exploit the full auto-suggestion request customization features that the what3words API provides by passing an instance of 
a CustomAutoSuggestRequest class to the what3wordsEditText class using the setCustomAutoSuggestRequest(customAutoSuggestRequest: CustomAutoSuggestRequest) function. You can create an instance of CustomAutoSuggestRequest through the CustomAutoSuggestRequest.Builder. The functions in the Builder are 
properly documented to guide you along the way. 

```kotlin
class CustomAutoSuggestRequest private constructor(
    var focus: Coordinates? = null,
    var clipToCountry: String = "",
    var clipToCircle: Pair<Coordinates, Double>? = null,
    var clipToPolygon: Coordinates? = null,
    var clipToBoundingBox: BoundingBox? = null,
    var numberOfResults: Int = 3,
    var numberOfFocusResults: Int = 0,
) {

    class Builder {
        private var focus: Coordinates? = null
        private var clipToCountry: String = ""
        private var clipToCircle: Pair<Coordinates, Double>? = null
        private var clipToPolygon: Coordinates? = null
        private var clipToBoundingBox: BoundingBox? = null
        private var numberOfResults: Int = 3
        private var numberOfFocusResults: Int = 0

        /**
         * This is a location, specified as a latitude (often where the user making the query is). If specified, the results will be weighted to
         * give preference to those near the <code>focus</code>. For convenience, longitude is allowed to wrap around the 180 line, so 361 is equivalent to 1.
         *
         * @param focus to use
         * @return a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun focus(focus: Coordinates?): Builder {
            this.focus = focus
            return this
        }

        /**
         * Restricts autosuggest to only return results inside the countries specified by comma-separated list of uppercase ISO 3166-1 alpha-2 country codes
         * (for example, to restrict to Belgium and the UK, use <code>clipToCountry("GB", "BE")</code>. <code>clipToCountry</code> will also accept lowercase
         * country codes. Entries must be two a-z letters. WARNING: If the two-letter code does not correspond to a country, there is no error: API simply
         * returns no results.
         *
         * @param countryCodes countries to clip results too
         * @return a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun clipToCountry(countryCodes: String): Builder {
            this.clipToCountry = countryCodes
            return this
        }

        /**
         * Restrict autosuggest results to a circle, specified by <code>Coordinates</code> representing the centre of the circle, plus the
         * <code>radius</code> in kilometres. For convenience, longitude is allowed to wrap around 180 degrees. For example 181 is equivalent to -179.
         *
         * @param coordinateAndRadius A Pair that represents centre the centre [Coordinates] of the circle and the radius of the circle in kilometres
         *
         * @return  a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun clipToCircle(coordinateAndRadius: Pair<Coordinates, Double>): Builder {
            this.clipToCircle = coordinateAndRadius
            return this
        }

        /**
         * Restrict autosuggest results to a polygon, specified by a collection of <code>Coordinates</code>. The polygon should be closed,
         * i.e. the first element should be repeated as the last element; also the list should contain at least 4 entries. The API is currently limited to
         * accepting up to 25 pairs.
         *
         * @param polygon the polygon to clip results too
         * @return  a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun clipToPolygon(polygon: Coordinates): Builder {
            this.clipToPolygon = polygon
            return this
        }

        /**
         * Restrict autosuggest results to a <code>BoundingBox</code>.
         *
         * @param boundingBox <code>BoundingBox</code> to clip results too
         * @return  a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun clipToBoundingBox(boundingBox: BoundingBox): Builder {
            this.clipToBoundingBox = boundingBox
            return this
        }

        /**
         * Set the number of AutoSuggest results to return. A maximum of 100 results can be specified, if a number greater than this is requested,
         * this will be truncated to the maximum. The default is 3
         *
         * @param numberOfResults the number of AutoSuggest results to return
         * @return  a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun numberOfResults(numberOfResults: Int): Builder {
            this.numberOfResults = numberOfResults
            return this
        }

        /**
         * Specifies the number of results (must be &lt;= nResults) within the results set which will have a focus. Defaults to <code>nResults</code>.
         * This allows you to run autosuggest with a mix of focussed and unfocussed results, to give you a "blend" of the two. This is exactly what the old V2
         * <code>standardblend</code> did, and <code>standardblend</code> behaviour can easily be replicated by passing <code>nFocusResults=1</code>,
         * which will return just one focussed result and the rest unfocussed.
         *
         * @param numberOfFocus number of results within the results set which will have a focus
         * @return a [CustomAutoSuggestRequest.Builder] instance suitable for invoking a <code>build</code> request
         */
        fun numberOfFocusResults(numberOfFocus: Int): Builder {
            this.numberOfFocusResults = numberOfFocus
            return this
        }

        /**
         * Builds the CustomAutoSuggestRequest object represented by the values set within this [CustomAutoSuggestRequest.Builder]
         *
         * @return a [CustomAutoSuggestRequest] instance
         */
        fun build(): CustomAutoSuggestRequest {
            return CustomAutoSuggestRequest(
                focus = this.focus,
                clipToCountry = this.clipToCountry,
                clipToCircle = this.clipToCircle,
                clipToPolygon = this.clipToPolygon,
                clipToBoundingBox = this.clipToBoundingBox,
                numberOfResults = this.numberOfResults,
                numberOfFocusResults = this.numberOfFocusResults
            )
        }
    }
}
```

## Sample app:

If you want to check different ways to use this library please look at the [**sample**](https://github.com/Pekwerike/what3words/tree/master/sample) app in this
repository for examples of how to use the **What3WordsEditText component**.



## Open Source Libraries 
- [what3words android wrapper](https://github.com/what3words/w3w-android-wrapper) - An Android library to use the what3words v3 API.
- [Mockito](https://site.mockito.org) - Mockito is a mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API. Mockito doesn’t give you hangover because the tests are very readable and they produce clean verification errors.
- [JUnit](https://junit.org/junit5/) - JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
- [Expresso](https://developer.android.com/training/testing/espresso) - Espresso is a testing framework for Android to make it easy to write reliable user interface tests.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
