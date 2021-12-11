# <img src="https://what3words.com/assets/images/w3w_square_red.png" width="64" height="64" alt="what3words">&nbsp;w3w-android-components

An Android library to use
the [what3words v3 API autosuggest](https://developer.what3words.com/public-api/docs#autosuggest).

### Demo

![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_1.png?raw=true "Screenshot 1")![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_2.png?raw=true "Screenshot 2")![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_3.png?raw=true "Screenshot 3")

To obtain an API key, please
visit [https://what3words.com/select-plan](https://what3words.com/select-plan) and sign up for an
account.

### Android minimum SDK support

[![Generic badge](https://img.shields.io/badge/minSdk-23-green.svg)](https://developer.android.com/about/versions/marshmallow/android-6.0/)

### Gradle

```
implementation 'com.what3words:w3w-android-components:2.1.0'
```

## Documentation

See the what3words public API [documentation](https://docs.what3words.com/api/v3/)

## Usage

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

activity_main.xml

```xml

<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.pekwerike.lib.ui.text.What3WordsEditText android:id="@+id/what3wordsEditText"
        app:layout_constraintStart_toStartOf="parent" app:layout_constraintTop_toTopOf="parent"
        android:layout_width="match_parent" android:layout_height="wrap_content"
        android:layout_margin="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
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
## General properties:

| property | default value | type | description | XML | Programmatically | |--|--|--|--|--|--| |
apiKey| *N/A* | String | Your what3words API key. **mandatory** | | :heavy_check_mark:
| hint | *e.g. lock.spout.radar* | String | Placeholder text to display in the input in its default
empty state. | :heavy_check_mark:
| errorMessage | *An error occurred. Please try again later* | String | Overwrite the generic error
message with a custom value. | :heavy_check_mark: | :heavy_check_mark:
| invalidAddressMessage | *No valid what3words address found* | String | Overwrite the validation
error message with a custom value. | :heavy_check_mark: | :heavy_check_mark:
| focus | *N/A* | Coordinates | This is a location, specified as a latitude/longitude (often where
the user making the query is). If specified, the results will be weighted to give preference to
those near the <code>focus</code> || :heavy_check_mark:
| clipToCountry | *N/A* | String | Clip results to a given country or comma separated list of
countries. Example value:"GB,US". || :heavy_check_mark:
| clipToCircle | *N/A* | Coordinates, Int | Clip results to a circle, specified by Coordinate(
lat,lng) and kilometres, where kilometres in the radius of the circle. || :heavy_check_mark:
| clipToBoundingBox | *N/A* | BoundingBox | Clip results to a bounding box specified using
co-ordinates. || :heavy_check_mark:
| clipToPolygon | *N/A* | List of Coordinates | Clip results to a bounding box specified using
co-ordinates. || :heavy_check_mark:
| returnCoordinates | *false* | Boolean | Calls the what3words API to obtain the coordinates for the
selected 3 word address (to then use on a map or pass through to a logistic company etc) |:
heavy_check_mark:| :heavy_check_mark:| | allowInvalid3wa | false | Boolean | Allow invalid 3 word
address ||:heavy_check_mark:| | suggestionsListPosition | *BELOW* | Enum | Suggestion list position
which can be `below`  (default) the EditText or `above` |:heavy_check_mark:|:heavy_check_mark:|

## Sample app:

If you want to check different ways to use this library please look at the **sample** app in this
repo for examples of how to use the **W3WAutoSuggestText component**.

![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_10.png?raw=true "Screenshot 10")

## Styles:

Use our base style as parent and you can set the custom properties available with XML on the table
above and the normal EditText styling, i.e:

```xml

<style name="YourCustomStyle" parent="Widget.AppCompat.W3WAutoSuggestEditText">
    <item name="android:textColor">#000000</item>
    <item name="android:textColorHint">#888888</item>
    <item name="invalidAddressMessage">Your custom invalid address message</item>
    <item name="errorMessage">Your custom error message</item>
    <item name="android:hint">Your custom placeholder</item>
    <item name="android:textAppearance">@style/YourCustomStyleTextAppearance</item>
</style>

<style name="YourCustomStyleTextAppearance" parent="TextAppearance.AppCompat">
<item name="android:textSize">22sp</item>
<item name="android:fontFamily">sans-serif-medium</item>
</style>
```

![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_4.png?raw=true "Screenshot 4")![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_5.png?raw=true "Screenshot 5")![alt text](https://github.com/what3words/w3w-autosuggest-edittext-android/blob/master/assets/screen_6.png?raw=true "Screenshot 6")

## Open Source Libraries 
- Mockito 
- JUnit 
- Expresso 
- Kotlin coroutines extension 

