package com.droidtitan.volleyexamples.rest.model.air

import com.google.gson.annotations.SerializedName

public class AirQualityHolder {

    SerializedName("reporte")
    var hourOfReport: Int = 0

    SerializedName("calidad")
    var airQuality: AirQuality? = null

    SerializedName("clima")
    var weather: Weather? = null

    SerializedName("uv")
    var uv: Uv? = null

    SerializedName("zonas")
    var zones: Zones? = null
}
