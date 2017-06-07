package com.droidtitan.volley.model.air

import com.google.gson.annotations.SerializedName

class AirQuality {

  @SerializedName("categoria") var category: String? = null

  @SerializedName("color") var color: String? = null

  @SerializedName("recomendaciones") var recommendation: String? = null
}
