package com.droidtitan.volleyexamples.rest.util;

import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import java.util.Map;

public final class RestUtils {

    public static final String ROOT_URL = "http://dev.datos.labplc.mx/";

    public static final String[] FILTERS = {"transporte_publico", "sedema_verificentros",
            "escuelas", "estacionamientos", "salud_hospitales", "ecobici_estaciones",
            "geolocalizacion_hoteles", "geolocalizacion_embajadas"};

    public static String getVehicleInfoUrlByPlate(String plate) {
        // http://dev.datos.labplc.mx/movilidad/vehiculos/436per.json
        return ROOT_URL + "movilidad/vehiculos/" + TextUtils.htmlEncode(plate) + ".json";
    }

    public static String getTaxiInfoByPlate(String plate) {
        // http://datos.labplc.mx/movilidad/taxis/A10291.json
        return ROOT_URL + "movilidad/taxis/" + TextUtils.htmlEncode(plate) + ".json";
    }

    public static String getTaxiDriverInfoById(String idNumber) {
        //http://datos.labplc.mx/movilidad/taxis/conductor.json?identificador=15676
        return ROOT_URL + "movilidad/taxis/conductor.json?identificador=" + TextUtils.htmlEncode(idNumber);
    }

    public static String getTaxiDriverInfoByFullName(String name, String paternalName, String maternalName) {
        //http://dev.datos.labplc.mx/movilidad/taxis/conductor.json?nombre=ramon&apellido_paterno=GONZALEZ&&apellido_materno=RANGEL

        StringBuilder sb = new StringBuilder();
        sb.append(ROOT_URL);
        sb.append("movilidad/taxis/conductor.json?");
        sb.append("nombre=" + TextUtils.htmlEncode(name));
        if (!TextUtils.isEmpty(paternalName)) {
            sb.append("&apellido_paterno=" + TextUtils.htmlEncode(paternalName));
        }

        if (!TextUtils.isEmpty(maternalName)) {
            sb.append("&apellido_materno=" + TextUtils.htmlEncode(maternalName));
        }

        return sb.toString();
    }

    /**
     * Updated every hour.
     */
    public static String getAirQualityUrl() {
        // http://datos.labplc.mx/aire.json

        return "http://datos.labplc.mx/aire.json";
    }

    /**
     * This url can return an object representing a death certificate, birth certificate, or marriage certificate
     *
     * @param idNumber Only digits number validate using TextUtils.is
     * @return
     */
    public static String getOfficalCertificateUrl(String idNumber) {
        // http://datos.labplc.mx/servicios/registrocivil/9720815.json
        // Validate using   TextUtils.isDigitsOnly(idNumber); can also set keyboard
        return ROOT_URL + "servicios/registrocivil/" + TextUtils.htmlEncode(idNumber) + ".json";
    }

    /**
     * Api for finding locations
     *
     * @param lat
     * @param lgn
     * @param radius
     * @return
     */
    public static String getLocationsUrl(double lat, double lgn, String radius) {
        // http://datos.labplc.mx/georeferencia.json?&latitud=19.443739&longitud=-99.182540&radio=500

        return ROOT_URL + "georeferencia.json?" + "&latitud=" + lat
                + "&longitud=" + lgn + "&radio=" + radius;
    }

    public static String getStreetImageViewUrl(double lat, double lng) {
        // http://maps.googleapis.com/maps/api/streetview?size=400x200&location=40.720032,-73.988354&heading=235&sensor=false
        return "http://maps.googleapis.com/maps/api/streetview?size=680x700&location=" + lat + "," + lng + "&heading=235&sensor=false";
    }

    /**
     * @param klass
     * @param method
     * @param url
     * @param headers
     * @param <T>     The response type.
     * @return A GsonRequest<T> that posts an HttpResponseEvent<T> with either the response if successful
     * or a VolleyError if uncessful.
     */
    public static <T> GsonRequest<T> getGsonRequest(Class<T> klass, int method, String url,
                                                    Map<String, String> headers) {

        return new GsonRequest<T>(method, url, klass, headers,
                new Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        Bus.postEvent(new HttpResponseEvent<T>(response));
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Bus.postEvent(new HttpResponseEvent<T>(error));
            }
        }
        );
    }

}
