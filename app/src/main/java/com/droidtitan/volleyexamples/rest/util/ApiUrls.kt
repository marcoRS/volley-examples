package com.droidtitan.volleyexamples.rest.util

public object ApiUrls {
    val ROOT_URL = "http://maps.googleapis.com/maps/api/staticmap?center="
    val BERKELEY_QUERY = "Berkeley,CA&zoom=12&size=1000x400&sensor=false"
    val SF_MAP_URL = "San+Francisco,CA&zoom=12&size=1000x400&sensor=false"

    /** Updated every hour. */
    public fun getAirQualityUrl(): String = "http://datos.labplc.mx/aire.json"

    public fun getBerkeleyMapUrl(): String = ROOT_URL + BERKELEY_QUERY

    public fun getSFmapUrl(): String = ROOT_URL + SF_MAP_URL
}
