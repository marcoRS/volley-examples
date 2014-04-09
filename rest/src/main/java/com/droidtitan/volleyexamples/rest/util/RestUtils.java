package com.droidtitan.volleyexamples.rest.util;

public final class RestUtils {
    private RestUtils() {
    }

    private static final String ROOT_URL = "http://maps.googleapis.com/maps/api/staticmap?center=";
    private static final String BERKELEY_QUERY = "Berkeley,CA&zoom=15&size=1000x400&sensor=false";
    private static final String SF_MAP_URL = "San+Francisco,CA&zoom=15&size=1000x400&sensor=false";

    /**
     * Updated every hour.
     */
    public static String getAirQualityUrl() {
        // http://datos.labplc.mx/aire.json

        return "http://datos.labplc.mx/aire.json";
    }

    public static String getBerkeleyMapUrl() {
        return ROOT_URL + BERKELEY_QUERY;
    }

    public static String getSfMapUrl() {
        return ROOT_URL + SF_MAP_URL;
    }

}
