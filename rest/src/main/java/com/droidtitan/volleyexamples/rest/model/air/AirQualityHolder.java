package com.droidtitan.volleyexamples.rest.model.air;

import com.google.gson.annotations.SerializedName;

public class AirQualityHolder {

    @SerializedName("reporte")
    int hourOfReport;

    @SerializedName("calidad")
    AirQuality airQuality;

    @SerializedName("clima")
    Weather weather;

    @SerializedName("uv")
    Uv uv;

    @SerializedName("zonas")
    Zones zones;

    public int getHourOfReport() {
        return hourOfReport;
    }

    public AirQuality getAirQuality() {
        return airQuality;
    }

    public Weather getWeather() {
        return weather;
    }

    public Uv getUv() {
        return uv;
    }

    public Zones getZones() {
        return zones;
    }
}
