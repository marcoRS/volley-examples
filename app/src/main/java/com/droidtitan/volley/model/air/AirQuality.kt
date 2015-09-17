package com.droidtitan.volley.model.air

import com.google.gson.annotations.SerializedName

public class AirQuality {

    @SerializedName("categoria")
    public var category: String? = null

    @SerializedName("color")
    public var color: String? = null

    @SerializedName("recomendaciones")
    public var recommendation: String? = null
}
