package com.droidtitan.volley.util

public object Api {
    val ERROR_KEY = "error"
    val ROOT_URL = "http://maps.googleapis.com/maps/api/staticmap?center="
    val BERKELEY_QUERY = "Berkeley,CA&zoom=12&size=1000x400&sensor=false"
    val SF_MAP_URL = "San+Francisco,CA&zoom=12&size=1000x400&sensor=false"

    /** Updated every hour. */
    public fun airQualityUrl(): String = "http://datos.labplc.mx/aire.json"

    public fun berkeleyMapUrl(): String = ROOT_URL + BERKELEY_QUERY

    public fun sfMapUrl(): String = ROOT_URL + SF_MAP_URL
}
