package com.droidtitan.volleyexamples.rest.model.air;

public class Uv {

    /**
     * Categoría del índice UV, puede ser: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 u 11
     */
    String indice;
    /**
     * color reprensenting the uv indice.
     */
    String color;
    /**
     * Recommendation for uv level.
     */
    String recomendaciones;

    public String getIndice() {
        return indice;
    }

    public String getColor() {
        return color;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }
}
