package com.droidtitan.volleyexamples.rest.util;

public final class ApiUrls {
    private ApiUrls() {
    }

    private static final String ROOT_URL = "http://maps.googleapis.com/maps/api/staticmap?center=";
    private static final String BERKELEY_QUERY = "Berkeley,CA&zoom=12&size=1000x400&sensor=false";
    private static final String SF_MAP_URL = "San+Francisco,CA&zoom=12&size=1000x400&sensor=false";

    /**
     * Updated every hour.
     */
    public static String getAirQualityUrl() {
        return "http://datos.labplc.mx/aire.json";
    }

    public static String getBerkeleyMapUrl() {
        return ROOT_URL + BERKELEY_QUERY;
    }

    public static String getSFmapUrl() {
        return ROOT_URL + SF_MAP_URL;
    }
}
