package com.droidtitan.volleyexamples.rest.util;

public final class RestUtils {

    public static final String BERKELEY_MAP_URL = "http://maps.googleapis.com/maps/api/staticmap?center=Berkeley,CA&zoom=15&size=1000x400&sensor=false";
    public static final String SAN_FRAN_MAP_URL = "http://maps.googleapis.com/maps/api/staticmap?center=San+Francisco,CA&zoom=15&size=1000x400&sensor=false";

    /**
     * Updated every hour.
     */
    public static String getAirQualityUrl() {
        // http://datos.labplc.mx/aire.json

        return "http://datos.labplc.mx/aire.json";
    }

}
