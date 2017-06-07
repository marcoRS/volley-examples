package com.droidtitan.volley.model.air

import com.google.gson.annotations.SerializedName

class AirQualityResponse {

  @SerializedName("consulta")
  var airQualityHolder: AirQualityHolder? = null

  fun category(): String? {
    return airQualityHolder?.airQuality?.category
  }

  fun getTemperature(): String? {
    return airQualityHolder?.weather?.temperature
  }
}
