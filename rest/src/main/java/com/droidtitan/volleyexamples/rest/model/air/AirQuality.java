package com.droidtitan.volleyexamples.rest.model.air;

import com.google.gson.annotations.SerializedName;

public class AirQuality {

    @SerializedName("categoria")
    String category;

    @SerializedName("color")
    String color;

    @SerializedName("recomendaciones")
    String recommendation;

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }

    public String getRecommendation() {
        return recommendation;
    }
}
