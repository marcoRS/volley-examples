package com.droidtitan.volleyexamples.rest.model.air;

import com.google.gson.annotations.SerializedName;

public class AirQualityResponse {

    @SerializedName("consulta")
    AirQualityHolder airQualityHolder;

    public AirQuality getAirQuality() {
        return airQualityHolder.getAirQuality();
    }

    public int getHourOfReport() {
        return airQualityHolder.getHourOfReport();
    }

    public Weather getWeather() {
        return airQualityHolder.getWeather();
    }

    public Uv getUv() {
        return airQualityHolder.getUv();
    }

    public Zones getZones() {
        return airQualityHolder.getZones();
    }
}
