package com.pekwerike.lib.domain


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
         * @param coordinates the focus to use
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
