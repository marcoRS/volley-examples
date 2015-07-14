package com.droidtitan.volley.model.air

import com.google.gson.annotations.SerializedName

public class AirQualityResponse {

    SerializedName("consulta")
    var airQualityHolder: AirQualityHolder? = null

    public fun category(): String? {
        return airQualityHolder?.airQuality?.category
    }

    public fun getTemperature(): String? {
        return airQualityHolder?.weather?.temperature
    }

}
